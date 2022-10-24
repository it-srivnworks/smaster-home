package com.srivn.works.smaster.smasterhome.model.users;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfo {

	private String title;
	private String firstName;
	private String lastName;
	private String userEmail;
	private int userType;

	@Builder
	public UserInfo(String title, String firstName, String lastName, String userEmail, int userType) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userEmail = userEmail;
		this.userType = userType;
	}

	
}
