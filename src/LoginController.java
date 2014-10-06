

import java.io.IOException;
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
@RolesAllowed({"Admin","User"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getRequestURI().equals("/MovieSite/login")){
			if(request.getSession(false) == null || request.getSession(false).getAttribute("userAuthenticated") != "true" ) {
				CinemaDataProcessor c = new CinemaDataProcessor();
				List<Movie> nowShowingMovies = c.findAllNowShowingMovies();
				request.setAttribute("nowShowingMovies", nowShowingMovies);
				List<Movie> comingSoonMovies = c.findAllComingSoonMovies();
				request.setAttribute("comingSoonMovies", comingSoonMovies);
				// TODO Sort Now Showing movies by Rating
				// TODO Sort Coming Soon movies by release date
				
				if(request.getAttribute("userExists") != null)
					request.setAttribute("loginFailure", "User does not exist");
				else if(request.getAttribute("userVerified") != null)
					request.setAttribute("loginFailure", "User not verified");
				else if(request.getParameter("submitLogin") != null)
					request.setAttribute("loginFailure", "Incorrect Password, please try again");
				
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else{
				response.sendRedirect("owner");
			}
		}
		else if(request.getParameter("submitLogout") != null){
			if(request.getSession(false) != null){
				request.getSession(false).invalidate();
				request.setAttribute("logoutMessage", "Successfully Logged Out");
			}
			response.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 if(request.getParameter("submitLogin") != null){
			 CinemaDataProcessor c = new CinemaDataProcessor();
			 UserMaster user;
			 if(c.findVerifiedUserByUsername(request.getParameter("username")) != null) {
				 user = c.findVerifiedUserByUsername(request.getParameter("username"));
				 if(user.getVerified() != 1){
					 request.setAttribute("userVerified", "false"); 
				 }
				 else if(request.getParameter("password").equals(user.getPassword())){
					 request.getSession().setAttribute("userAuthenticated", "true");
					 request.getSession().setAttribute("username", user.getUsername());
					 request.getSession().setAttribute("firstname", user.getFirstName());
					 request.getSession().setAttribute("nickname", user.getNickName());
				 }
			 }
			 else
				 request.setAttribute("userExists", "false");
			 // TODO Reasons for login failure - does not exist, incorrect pw, not authenticated
			 doGet(request,response);
		 }
	}

}
