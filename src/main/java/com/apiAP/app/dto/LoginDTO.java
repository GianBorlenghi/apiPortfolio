package com.apiAP.app.dto;


public class LoginDTO {
	
	private String mail;
	private String password;
	public LoginDTO() {
		super();
	}
	public LoginDTO(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
