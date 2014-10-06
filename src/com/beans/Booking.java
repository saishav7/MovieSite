package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bookings")
public class Booking {
	@Id
    @Column(name = "bkid")
    private String bkid;
    @Column(name = "usrname")
    private String usrname;
    @Column(name = "movie_title")
    private String movieTitle;
    @Column(name = "cinema_location")
    private String cinemaLocation;
    @Column(name = "dates")
    private String dates;
    @Column(name = "timings")
    private String timings;
    @Column(name = "noofseats")
    private Integer noofseats;
    @Column(name = "crcard")
    private String crcard;

    public String getBkid() {
		return bkid;
	}
	public void setBkid(String bkid) {
		this.bkid = bkid;
	}

    public String getusrname() {
        return usrname;
    }
    public void setusrname(String usrname) {
        this.usrname = usrname;
    }
    public String getmovietitle() {
        return movieTitle;
    }
    public void setmovietitle(String movietitle) {
        this.movieTitle = movietitle;
    }
    public String getcinemalocation() {
        return cinemaLocation;
    }
    public void setcinemalocation(String cinemalocation) {
        this.cinemaLocation = cinemalocation;
    }
    public String getdates() {
        return dates;
    }
    public void setdates(String dates) {
        this.dates = dates;
    }
    public String gettimings() {
        return timings;
    }
    public void settimings(String timings) {
        this.timings = timings;
    }
    public Integer getnoofseats() {
        return noofseats;
    }
    public void setnoofseats(Integer noofseats) {
        this.noofseats = noofseats;
    }
    public String getcrcard() {
        return crcard;
    }
    public void setcrcard(String crcard) {
        this.crcard = crcard;
    }
}