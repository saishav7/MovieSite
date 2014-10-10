
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.beans.Cinema;
import com.beans.Comment;
import com.beans.Movie;
import com.beans.UserMaster;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet(name="CustomerController",urlPatterns={"/register","/edituser","/search","/searchResult","/searchDetails"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB

public class CustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String filePath = "";
    private String uploadMessage = "";
    private int userAuthenticated = 0;
       
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
            if(request.getParameter("nam") != null) {
                // TODO Input Checks
                CinemaDataProcessor c = new CinemaDataProcessor();
                // TODO handle case when user with name exists
                c.addUser(request.getParameter("nam"), request.getParameter("fnam"), request.getParameter("lnam"), request.getParameter("nnam"), request.getParameter("pwd"), request.getParameter("eml"));
                //response.sendRedirect("edituser.jsp");
                response.sendRedirect("search");
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
}
