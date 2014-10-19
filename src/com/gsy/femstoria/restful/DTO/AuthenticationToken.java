package com.gsy.femstoria.restful.DTO;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gsy.femstoria.restful.Formatter.CustomJsonDateDeserializer;

public class AuthenticationToken implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5970012467025374160L;

	public String accessToken;

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public Date dateIssued;
}
