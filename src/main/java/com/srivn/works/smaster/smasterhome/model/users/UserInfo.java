package com.srivn.works.smaster.smasterhome.model.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {

	private String title;
	private String firstName;
	private String lastName;
	private String userEmail;

}
