package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usrcmnt")
public class Comment {
	@Id
    @Column(name = "movie_title")
    private String movieTitle;
    @Column(name = "username")
    private String usrname;
    @Column(name = "usernname")
    private String usrnname;
    @Column(name = "cmnt")
    private String cmnt;
    @Column(name = "cmntdate")
    private String cmntdate;
    @Column(name = "usrrating")
    private int rating;
 
    public String getmovietitle() {
        return movieTitle;
    }
    public void setmovietitle(String movietitle) {
        this.movieTitle = movietitle;
    }
    public String getusrname() {
        return usrname;
    }
    public void setusrname(String usrname) {
        this.usrname = usrname;
    }
    public String getusrnname() {
        return usrnname;
    }
    public void setusrnname(String usrnname) {
        this.usrnname = usrnname;
    }
    public String getcmnt() {
        return cmnt;
    }
    public void setcmnt(String cmnt) {
        this.cmnt = cmnt;
    }
    public String getcmntdate() {
        return cmntdate;
    }
    public void setcmntdate(String cmntdate) {
        this.cmntdate = cmntdate;
    }
    public Integer getusrrating() {
        return rating;
    }
    public void setusrrating(Integer usrrating) {
        this.rating = usrrating;
    }
}
