

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.beans.Cinema;
import com.beans.Movie;
import com.beans.UserMaster;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(name="HomeController",urlPatterns={"/owner","/addCinema","/addMovie","/addShowtime","/uploadPoster","/poster"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
@DeclareRoles({"Admin", "User"})
@RolesAllowed("Admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath = "";
	private String uploadMessage = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
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
			request.setAttribute("message", uploadMessage);
			if(request.getParameter("movieForm") != null) {
				// TODO Input Checks
				CinemaDataProcessor c = new CinemaDataProcessor();
			
				// TODO Handle case when movie with title exists
				try {
					c.addMovie(request.getParameter("title"),filePath,
								request.getParameter("starcast"),request.getParameter("genre"),
								request.getParameter("director"),request.getParameter("synopsis"),
								request.getParameter("rating"),	request.getParameter("releaseDate"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				uploadMessage = "";
				response.sendRedirect("owner");
			}
			else
				request.getRequestDispatcher("movie.jsp").forward(request, response);
		}
		
		else if(request.getRequestURI().equals("/MovieSite/poster")) {
		 if(request.getAttribute("message") == null) 
			request.getRequestDispatcher("poster.jsp").forward(request, response);
		 else
			 response.sendRedirect("addMovie");	
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
		else{
			request.getRequestDispatcher("ownersPortal.jsp").forward(request, response);
		}
	}
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length()-1);
			}
		}
	return "";
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 if(request.getParameter("posterVar") != null){
			// gets absolute path of the web application
	        String appPath = request.getServletContext().getRealPath("");
	        // constructs path of the directory to save uploaded file
	        String savePath = appPath + File.separator + "posters";
	         
	        // creates the save directory if it does not exists
	        File fileSaveDir = new File(savePath);
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdir();
	        }
	        
	        for (Part part : request.getParts()) {
	            String fileName = extractFileName(part);
	            if(fileName != "") {
	            	filePath = "posters" + File.separator + fileName;
	            	part.write(savePath + File.separator + fileName);
	            }
	        }
	        uploadMessage = "File Selected";
	        request.setAttribute("message", uploadMessage);
	        doGet(request, response);
		}
	}
	
}
