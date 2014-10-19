package com.gsy.femstoria.model;

public class NewUser {
	
	public String userName;
	public String password;
	public String confirmPassword;
	public String email;
	
	public NewUser(String userName, String password, String confirmPassword, String email){
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
	}

}
