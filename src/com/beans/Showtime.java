package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="showtimes")
public class Showtime {
	@Id
	@Column(name="movie_title")
    private String movieTitle;
	@Column(name="cinema_location")
    private String cinemaLocation;
    private String timings;
    
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getCinemaLocation() {
		return cinemaLocation;
	}
	public void setCinemaLocation(String cinemaLocation) {
		this.cinemaLocation = cinemaLocation;
	}
	public String getTimings() {
		return timings;
	}
	public void setTimings(String timings) {
		this.timings = timings;
	}
   
}           