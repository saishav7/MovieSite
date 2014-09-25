

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Cinema;
import com.beans.Movie;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(name="HomeController",urlPatterns={"/owner","/addCinema","/addMovie","/addShowtime","/login"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getRequestURI().equals("/MovieSite/addCinema")){
			if(request.getParameter("location") != null) {
				// TODO Input Checks
				CinemaDataProcessor c = new CinemaDataProcessor();
				// TODO handle case when cinema with location exists
				Map<String,String[]> items = (request.getParameterMap());
				String []amenitiesArr = items.get("amenities");
				StringBuilder amenitiesBuilder = new StringBuilder();
				for (String amenities : amenitiesArr) {
					amenitiesBuilder.append(amenities);
					if(!(amenities.equals(amenitiesArr[amenitiesArr.length - 1])))
						amenitiesBuilder.append(",");
				}
				c.addCinema(request.getParameter("location"), Integer.parseInt(request.getParameter("seatingCap")), amenitiesBuilder.toString());
				response.sendRedirect("owner");
			}
			else
				request.getRequestDispatcher("cinema.jsp").forward(request, response);
		}
		else if(request.getRequestURI().equals("/MovieSite/addMovie")){
			if(request.getParameter("title") != null) {
				// TODO Input Checks
				CinemaDataProcessor c = new CinemaDataProcessor();
				// TODO Handle case when movie with title exists
				try {
					// TODO Poster image
					c.addMovie(request.getParameter("title"),request.getParameter("poster"),
								request.getParameter("starcast"),request.getParameter("genre"),
								request.getParameter("director"),request.getParameter("synopsis"),
								request.getParameter("rating"),	request.getParameter("releaseDate"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				response.sendRedirect("owner");
			}
			else
				request.getRequestDispatcher("movie.jsp").forward(request, response);
		}
		else if(request.getRequestURI().equals("/MovieSite/addShowtime")){
			CinemaDataProcessor c = new CinemaDataProcessor();
			List<Movie> nowShowingMovies = c.findAllNowShowingMovies();
			request.setAttribute("nowShowingMovies", nowShowingMovies);
			List<Cinema> allCinemas = c.findAllCinemas();
			request.setAttribute("allCinemas", allCinemas);
			
			if(request.getParameter("timings") != null) {
				// TODO check if timings exist for the cinema and movie combination, same movie can have different cinemas
				c.addShowtime(request.getParameter("cinema_location"), request.getParameter("movie_title"), request.getParameter("timings"));
				response.sendRedirect("owner");
			}
			else
				request.getRequestDispatcher("showtime.jsp").forward(request, response);
		}
		else if(request.getRequestURI().equals("/MovieSite/login")){
			CinemaDataProcessor c = new CinemaDataProcessor();
			List<Movie> nowShowingMovies = c.findAllNowShowingMovies();
			request.setAttribute("nowShowingMovies", nowShowingMovies);
			List<Movie> comingSoonMovies = c.findAllComingSoonMovies();
			request.setAttribute("comingSoonMovies", comingSoonMovies);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("ownersPortal.jsp").forward(request, response);
		}
	}
	
}
