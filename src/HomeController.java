

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(name="HomeController",urlPatterns={"/owner","/addCinema","/addMovie","/addShowtime"})
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
					c.addMovie(request.getParameter("title"),request.getParameter("title"),
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
		else{
			request.getRequestDispatcher("ownersPortal.jsp").forward(request, response);
		}
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
}
