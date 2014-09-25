import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.beans.Cinema;
import com.beans.Movie;
import com.beans.Showtime;


public class CinemaDataProcessor {
	
	private static final Logger log = Logger.getLogger( ClassName.class.getName() );

	
	public Session connectToDatabase(){
		log.info("Trying to create a connection with the database.");
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        Session session = sessionFactory.openSession();
        log.info("Connection with the database created successfuly.");
        return session;
		
	}
	public void addCinema(String location, int seatingCapacity, String amenities) {
		
        Session session = connectToDatabase();
        
        session.beginTransaction();
 
        Cinema cinema = new Cinema();
        
        if(location != null)
        	cinema.setLocation(location);
        	cinema.setSeatingCapacity(seatingCapacity);
        if(amenities != null)
        	cinema.setAmenities(amenities);
           
        session.save(cinema);
        session.getTransaction().commit();
        session.close();

        log.info("Created cinema entry for " + location);
    }
	
	public void addMovie(String title, String poster, String starCast, String genre, String director, String synopsis, String ageRating, String releaseDate) throws ParseException {
			
	        Session session = connectToDatabase();
	        
	        session.beginTransaction();
	 
	        Movie movie = new Movie();
	        
	        movie.setTitle(title);
	        if(poster != null)
	        	movie.setPoster(poster);
	        if(starCast != null)
	        	movie.setStarCast(starCast);
	        if(genre != null)
	        	movie.setGenre(genre);
	        if(director != null)
	        	movie.setDirector(director);
	        if(synopsis != null)
	        	movie.setSynopsis(synopsis);
	        if(ageRating != null)
	        	movie.setAgeRating(ageRating);
	        if(releaseDate != null) {
	        	
	        	movie.setReleaseDate(releaseDate);
		        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		        Calendar cal  = Calendar.getInstance();
		        cal.setTime(df.parse(releaseDate));
		        Calendar today = Calendar.getInstance();
		        
		        if(cal.getTime().compareTo(today.getTime()) > 0)
		        	movie.setStatus("Coming Soon");
		        else
		        	movie.setStatus("Now Showing");
	        
	        }
	        session.save(movie);
	        session.getTransaction().commit();
	        session.close();
	
	        log.info("Created movie entry for " + title);
	}	
	
public void addShowtime(String cinema_location, String movie_title, String timings) {
		
        Session session = connectToDatabase();
        
        session.beginTransaction();
 
        Showtime showtime = new Showtime();
        
        if(cinema_location != null)
        	showtime.setCinemaLocation(cinema_location);
        if(movie_title != null)
        	showtime.setMovieTitle(movie_title);
        showtime.setTimings(timings);
        session.save(showtime);
        session.getTransaction().commit();
        session.close();

        log.info("Created showtime entry for " + cinema_location + " " + movie_title);
    }
	
	public List<Movie> findAllNowShowingMovies() {
		Session session = connectToDatabase();
		session.beginTransaction();
		Query query = session.createQuery("from Movie");
		List<Movie> movieList = new ArrayList<Movie>();
		java.util.List allMovies;  
		allMovies = query.list();  
		  for (int i = 0; i < allMovies.size(); i++) {  
		   Movie movie = (Movie) allMovies.get(i);
		   if(movie.getStatus().equals("Now Showing"))
			   movieList.add(movie);
		  }  
        return movieList;
    }
	
	public List<Movie> findAllComingSoonMovies() {
		Session session = connectToDatabase();
		session.beginTransaction();
		Query query = session.createQuery("from Movie");
		List<Movie> movieList = new ArrayList<Movie>();
		java.util.List allMovies;  
		allMovies = query.list();  
		  for (int i = 0; i < allMovies.size(); i++) {  
		   Movie movie = (Movie) allMovies.get(i);
		   if(movie.getStatus().equals("Coming Soon"))
			   movieList.add(movie);
		  }  
        return movieList;
    }
	
	public List<Cinema> findAllCinemas() {
		Session session = connectToDatabase();
		session.beginTransaction();
		Query query = session.createQuery("from Cinema");
		List<Cinema> cinemaList = new ArrayList<Cinema>();
		java.util.List allCinemas;  
		allCinemas = query.list();  
		  for (int i = 0; i < allCinemas.size(); i++) {  
		   Cinema cinema = (Cinema) allCinemas.get(i);
		   cinemaList.add(cinema);
		  }  
        return cinemaList;
    }
}
