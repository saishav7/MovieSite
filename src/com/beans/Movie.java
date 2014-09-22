package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movies")
public class Movie {
	
		@Id
		private String title;
		private String poster;
	    @Column(name = "star_cast")
	    private String starCast;
	    private String genre;
	    private String director;
	    private String synopsis;
	    @Column(name = "age_rating")
	    private String ageRating;
	    @Column(name = "release_date")
	    private String releaseDate;
	    private String status;
	    
		public String getReleaseDate() {
			return releaseDate;
		}
		public void setReleaseDate(String releaseDate) {
			this.releaseDate = releaseDate;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getPoster() {
			return poster;
		}
		public void setPoster(String poster) {
			this.poster = poster;
		}
		public String getStarCast() {
			return starCast;
		}
		public void setStarCast(String starCast) {
			this.starCast = starCast;
		}
		public String getGenre() {
			return genre;
		}
		public void setGenre(String genre) {
			this.genre = genre;
		}
		public String getDirector() {
			return director;
		}
		public void setDirector(String director) {
			this.director = director;
		}
		public String getSynopsis() {
			return synopsis;
		}
		public void setSynopsis(String synopsis) {
			this.synopsis = synopsis;
		}
		public String getAgeRating() {
			return ageRating;
		}
		public void setAgeRating(String ageRating) {
			this.ageRating = ageRating;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	 
}
