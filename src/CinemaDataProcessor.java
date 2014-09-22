import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.beans.Cinema;
import com.beans.Movie;


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

        log.info("Created cinema entry for" + cinema);
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
	
	        log.info("Created movie entry for" + title);
	}	
}
