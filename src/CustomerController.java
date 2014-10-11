
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name="CustomerController",urlPatterns={"/register","/editUser","/search","/searchResult","/searchDetails"})
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
					c.verifyUser(decrypt("aumoviesaumovies", request.getParameter("unknownVal").getBytes()));
					response.sendRedirect("search.jsp");
				} catch (GeneralSecurityException e) {
					e.printStackTrace();
				}
    		}
    		else if(request.getParameter("nam") != null) {
            	// TODO add activation.jar and mail.jar to tomcat lib folder
                // TODO Input Checks
                CinemaDataProcessor c = new CinemaDataProcessor();
                // TODO handle case when user with name exists
                c.addUser(request.getParameter("nam"), request.getParameter("fnam"), request.getParameter("lnam"), request.getParameter("nnam"), request.getParameter("pwd"), request.getParameter("eml"));
                //response.sendRedirect("edituser.jsp");
                try {
					sendEmailMessage(request.getParameter("eml"), encrypt("aumoviesaumovies",request.getParameter("nam")));
				System.out.println(encrypt("aumoviesaumovies",request.getParameter("nam")));
                } catch (GeneralSecurityException e) {
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
        else if(request.getRequestURI().equals("/MovieSite/editUser")){
        	if(request.getParameter("edit") != null){
        		CinemaDataProcessor c = new CinemaDataProcessor();
        		c.editUser(request.getSession(false).getAttribute("username").toString(), request.getParameter("firstname"), request.getParameter("lastname"),  request.getParameter("nickname"), request.getParameter("email"));
        		response.sendRedirect("search.jsp");
        	}
        	else{
        		CinemaDataProcessor c = new CinemaDataProcessor();
	        	UserMaster usr = c.findVerifiedUserByUsername(request.getSession(false).getAttribute("username").toString());
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
    
    
    public void sendEmailMessage(String to, byte[] encrypted){
		MailSender sender = null;
		try{
			sender = MailSender.getMailSender();
			String fromAddress = "auaumovies@gmail.com";
			String toAddress = to;
			String subject = "Registration";
			StringBuffer mailBody = new StringBuffer();
			mailBody.append("Welcome to AU Movies <br> Please Click on the link below to complete registration <br> <a href='http://localhost:8080/MovieSite/register?unknownVal="+ 
					encrypted+"&confirmRegistration=true'>Complete Registration</a>");
			sender.sendMessage(fromAddress, toAddress, subject, mailBody);
 		}catch(Exception e){
 			System.out.println("here");
			e.printStackTrace();
		}
	}
    
    public static byte[] encrypt(String key, String value)
    	      throws GeneralSecurityException {

	    byte[] raw = key.getBytes(Charset.forName("US-ASCII"));
	    if (raw.length != 16) {
	      throw new IllegalArgumentException("Invalid key size.");
	    }

	    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    cipher.init(Cipher.ENCRYPT_MODE, skeySpec,
	        new IvParameterSpec(new byte[16]));
	    return cipher.doFinal(value.getBytes(Charset.forName("US-ASCII")));
	  }

	  public static String decrypt(String key, byte[] encrypted)
	      throws GeneralSecurityException {

	    byte[] raw = key.getBytes(Charset.forName("US-ASCII"));
	    if (raw.length != 16) {
	      throw new IllegalArgumentException("Invalid key size.");
	    }
	    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    cipher.init(Cipher.DECRYPT_MODE, skeySpec,
	        new IvParameterSpec(new byte[16]));
	    byte[] original = cipher.doFinal(encrypted);

	    return new String(original, Charset.forName("US-ASCII"));
    }
    	
}
