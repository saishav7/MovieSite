package com.beans;

import java.util.Date;

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
    @Column(name = "usrname")
    private String username;
    @Column(name = "usrnname")
    private String userNickName;
    @Column(name = "cmnt")
    private String commentText;
    @Column(name = "cmntdate")
    private Date commentDate;
    @Column(name = "usrrating")
    private int rating;
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String uesrNickName) {
		this.userNickName = uesrNickName;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
 
}
