package com.ramkarlapudi.EnquiryApp.model;

import org.springframework.stereotype.Component;

@Component
public class Credientials {
	
	public String Username;
	public String Password;
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	

}
