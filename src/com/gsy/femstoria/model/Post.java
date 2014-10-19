package com.gsy.femstoria.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gsy.femstoria.restful.Formatter.CustomJsonDateDeserializer;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5015300464958613284L;
	public String userId;
	public String userName;
	public Date postedDay;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public Date postedTime;
	public String message;
	public int hugs;
	public boolean huged;
	public String postId;
	public String modifiedTime;	
	public Post(){
		
	}

	public Post(String id, String content, String userName, Date datePosted) {
		this.userId = id;
		this.message = content;
		this.userName = userName;
		this.postedTime = datePosted;
	}

	@Override
	public String toString() {
		return message;
	}

}
