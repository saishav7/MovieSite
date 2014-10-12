

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Movie;
import com.beans.UserMaster;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(name="LoginController",urlPatterns={"/login","/logout"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getRequestURI().equals("/MovieSite/login")){
			if(request.getSession(false) == null || request.getSession(false).getAttribute("userAuthenticated") != "true" ) {
				CinemaDataProcessor c = new CinemaDataProcessor();
				List<Movie> nowShowingMovies = c.findAllNowShowingMovies();
				List<Movie> comingSoonMovies = c.findAllComingSoonMovies();
				
				
				Collections.sort(comingSoonMovies, new Comparator<Movie>() {
				    @Override public int compare(Movie p1, Movie p2) {
				    	Date p1Date = new Date();
				    	Date p2Date = new Date();
				    	
						try {
							p1Date = new SimpleDateFormat("dd/MM/yyyy").parse(p1.getReleaseDate());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						try {
							p2Date = new SimpleDateFormat("dd/MM/yyyy").parse(p2.getReleaseDate());
						} catch (ParseException e) {
							e.printStackTrace();
						}
				    	if (p1Date.before(p2Date)) {
				            return -1;
				        } else if (p1Date.after(p2Date)) {
				            return 1;
				        } else {
				            return 0;
				        }        
				    }
				});
				
				Collections.sort(nowShowingMovies, new Comparator<Movie>() {
				    @Override public int compare(Movie p1, Movie p2) {
				    	CinemaDataProcessor c = new CinemaDataProcessor();
				    	int r1 = c.findAvgRatingByMovie(p1.getTitle());
				    	int r2 = c.findAvgRatingByMovie(p2.getTitle());
				    	return r2 - r1;
				    }
				});
		
				request.setAttribute("nowShowingMovies", nowShowingMovies);
				request.setAttribute("comingSoonMovies", comingSoonMovies);
				
				if(request.getAttribute("userExists") != null)
					request.setAttribute("loginFailure", "User does not exist");
				else if(request.getAttribute("userVerified") != null)
					request.setAttribute("loginFailure", "User not verified");
				else if(request.getParameter("submitLogin") != null)
					request.setAttribute("loginFailure", "Incorrect Password, please try again");
				
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else if(request.getSession(false).getAttribute("username").equals("admin"))
				response.sendRedirect("owner");
			else
				response.sendRedirect("search");
			
		}
		else if(request.getParameter("submitLogout") != null){
			if(request.getSession(false) != null){
				request.getSession(false).invalidate();
				request.setAttribute("logoutMessage", "Successfully Logged Out");
			}
			response.sendRedirect("login");
			// TODO Logout message not showing due to redirection
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 if(request.getParameter("submitLogin") != null){
			 CinemaDataProcessor c = new CinemaDataProcessor();
			 UserMaster user;
			 if(c.findUserByUsername(request.getParameter("username")) != null) {
				 user = c.findUserByUsername(request.getParameter("username"));
				 if(user.getVerified() != 1){
					 request.setAttribute("userVerified", "false"); 
				 } else
					try {
						if(Md5Digest.main(request.getParameter("password")).equals(user.getPassword())){
							 request.getSession().setAttribute("userAuthenticated", "true");
							 request.getSession().setAttribute("username", user.getUsername());
							 request.getSession().setAttribute("firstname", user.getFirstName());
							 request.getSession().setAttribute("nickname", user.getNickName());
						 }
					} catch (Exception e) {
						e.printStackTrace();
					}
			 }
			 else
				 request.setAttribute("userExists", "false");
			 doGet(request,response);
		 }
	}

}
