package com.srivn.works.smaster.smasterhome.model.users;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegistration {

	private String firstName;
	private String lastName;
	private String userEmail;
	private String userPassword;
	private int userType;

	@Builder
	public UserRegistration(String firstName, String lastName, String userEmail, String userPassword, int userType) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userType = userType;
	}
}
