
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Cinema;
import com.beans.Comment;
import com.beans.Movie;
import com.beans.UserMaster;
import com.helper.MailSender;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet(name="CustomerController",urlPatterns={"/register","/editUser","/search","/searchResult","/searchDetails","/comment"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB

public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(CustomerController.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getRequestURI().equals("/MovieSite/register")){
    		if(request.getParameter("confirmRegistration") != null && request.getParameter("unknownVal") != null){
    			CinemaDataProcessor c = new CinemaDataProcessor();
				try {
					UserMaster u = c.findUserByUsername(request.getParameter("confirmRegistration"));
					if(u.getEncryptedVal().equals(request.getParameter("unknownVal"))){
						c.verifyUser(request.getParameter("confirmRegistration"));
						System.out.println("Verification Complete");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.sendRedirect("search.jsp");
    		}
    		else if(request.getParameter("nam") != null) {
            	// TODO add activation.jar and mail.jar to tomcat lib folder
                // TODO Input Checks
                CinemaDataProcessor c = new CinemaDataProcessor();
                // TODO handle case when user with name exists
                String encrypt="";
				try {
					encrypt = Md5Digest.main(request.getParameter("nam"));
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
                try {
					c.addUser(request.getParameter("nam"), request.getParameter("fnam"), request.getParameter("lnam"), request.getParameter("nnam"), Md5Digest.main(request.getParameter("pwd")), request.getParameter("eml"),encrypt);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
                try {
					sendEmailMessage(request.getParameter("eml"), encrypt, request.getParameter("nam"));
				} catch (Exception e) {
					e.printStackTrace();
				}
                response.sendRedirect("search.jsp");
            }
            else
                request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        else if(request.getRequestURI().equals("/MovieSite/search")){
        	if(request.getParameter("genre") != null) {
	        	System.out.println("Search "+request.getParameter("genre")+":"+request.getParameter("title"));
	        	CinemaDataProcessor c = new CinemaDataProcessor();
				List<Movie> searchMovies = c.searchMovies(request.getParameter("genre"), request.getParameter("title"));
				request.setAttribute("searchMovies", searchMovies);
				response.sendRedirect("searchResult.jsp");}
        	else
        		response.sendRedirect("search.jsp");
        }
        else if(request.getRequestURI().equals("/MovieSite/searchResult")){
        	if(request.getParameter("title") != null) {
	        	System.out.println("SResult "+request.getParameter("genre")+":"+request.getParameter("title"));
	        	CinemaDataProcessor c = new CinemaDataProcessor();
				List<Movie> movieDetails = c.movieDetails(request.getParameter("title"));
				request.setAttribute("movieDetails", movieDetails);
				List<Comment> movieComments = c.movieComments(request.getParameter("title"));
				request.setAttribute("movieComments", movieComments);
				List<Cinema> movieCinemas = c.movieCinemas(request.getParameter("title"), request.getParameter("releaseDate"));
				request.setAttribute("movieCinemas", movieCinemas);
				response.sendRedirect("searchDetails.jsp");}
			else
				response.sendRedirect("search.jsp");
        }
        else if(request.getRequestURI().equals("/MovieSite/comment")){
        	if(request.getParameter("cTitle") != null && (request.getParameter("rating") != null || request.getParameter("comment") != null)){
        		CinemaDataProcessor c = new CinemaDataProcessor();
        		if(request.getSession(false).getAttribute("username") != null && request.getSession(false).getAttribute("nickname") != null
        			&& c.isNowShowing(request.getParameter("cTitle")) == true) {
		        	c.addComment(request.getSession(false).getAttribute("username").toString(), request.getSession(false).getAttribute("nickname").toString(), 
		        				request.getParameter("cTitle"), request.getParameter("comment"), 
		        				Integer.parseInt(request.getParameter("rating")));
		        	response.sendRedirect("searchResult?dettitle="+request.getParameter("cTitle"));
        		}
        		else if(request.getSession(false).getAttribute("username") == null) {
	        		response.sendError(HttpServletResponse.SC_FORBIDDEN);
        		}
        		else {
        			request.setAttribute("errorMessage", "Movie has not been released yet");
        			response.sendRedirect("errors.jsp");
        		}
        		
        	}
        	else if(request.getParameter("cTitle") != null){
        		request.setAttribute("movie", request.getParameter("cTitle"));
        		request.getRequestDispatcher("comment.jsp").forward(request, response);
        		
        	}
        	else{
        		request.setAttribute("errorMessage", "Mo movie to add comments for");
				response.sendRedirect("error.jsp");
        	}
        	
        }
        else if(request.getRequestURI().equals("/MovieSite/editUser")){
        	if(request.getParameter("edit") != null){
        		CinemaDataProcessor c = new CinemaDataProcessor();
        		c.editUser(request.getSession(false).getAttribute("username").toString(), request.getParameter("firstname"), request.getParameter("lastname"),  request.getParameter("nickname"), request.getParameter("email"));
        		response.sendRedirect("search.jsp");
        	}
        	else{
        		CinemaDataProcessor c = new CinemaDataProcessor();
	        	UserMaster usr = c.findUserByUsername(request.getSession(false).getAttribute("username").toString());
	        	request.setAttribute("usr", usr);
	        	request.getRequestDispatcher("editUser.jsp").forward(request, response);
        	}
        }
        else{
            request.getRequestDispatcher("search.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request,response);
    }
    
    
    public void sendEmailMessage(String to, String encrypted, String user){
		MailSender sender = null;
		try{
			sender = MailSender.getMailSender();
			String fromAddress = "auaumovies@gmail.com";
			String toAddress = to;
			String subject = "Registration";
			StringBuffer mailBody = new StringBuffer();
			encrypted = URLEncoder.encode(encrypted,"UTF-8");
			mailBody.append("Welcome to AU Movies <br> Please Click on the link below to complete registration <br> http://localhost:8080/MovieSite/register?unknownVal="+ 
					encrypted+"&confirmRegistration="+user);
			sender.sendMessage(fromAddress, toAddress, subject, mailBody);
 		}catch(Exception e){
 			System.out.println("here");
			e.printStackTrace();
		}
	}
    
}
