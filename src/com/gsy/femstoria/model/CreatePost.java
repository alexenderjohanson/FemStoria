package com.gsy.femstoria.model;

public class CreatePost {

	public String userId;
	public String userName;
	public String message;
	
	public CreatePost(String userId, String userName, String message){
		this.userId = userId;
		this.userName = userName;
		this.message = message;
	}
}
