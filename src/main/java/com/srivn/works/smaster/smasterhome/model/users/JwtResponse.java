package com.srivn.works.smaster.smasterhome.model.users;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	
	private final String userEmail;
	private final String jwttoken;

	public JwtResponse(String userEmail, String jwttoken) {
		this.userEmail = userEmail;
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getUserEmail() {
		return userEmail;
	}
	
}