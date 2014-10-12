package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usrmst")
public class UserMaster {
	@Id
	@Column(name = "usrname")
    private String username;
    @Column(name = "usrpassword")
    private String password;
    @Column(name = "usrfname")
    private String firstName;
    @Column(name = "usrlname")
    private String lastName;
    @Column(name = "usrnname")
    private String nickName;
    @Column(name = "usremail")
    private String userEmail;
    @Column(name = "usremailvrf")
    private int verified;
    @Column(name = "encrypted")
    private String encryptedVal;
	
	public String getEncryptedVal() {
		return encryptedVal;
	}
	public void setEncryptedVal(String encryptedVal) {
		this.encryptedVal = encryptedVal;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getVerified() {
		return verified;
	}
	public void setVerified(int verified) {
		this.verified = verified;
	}
 
}           