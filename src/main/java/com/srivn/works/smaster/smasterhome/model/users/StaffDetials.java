package com.srivn.works.smaster.smasterhome.model.users;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffDetials extends UserInfo{

	private Date dob;
	private int picId;
	private String mobile;
	private AddressInfo primaryAddress;
	private Date inDate;
	private Date outDate;
	
	private String profileTitle;
	private String profileDescription;
	private String dept;
	
	public StaffDetials(String title, String firstName, String lastName, String userEmail, int userType) {
		super(title, firstName, lastName, userEmail, userType);
		// TODO Auto-generated constructor stub
	}

		
	
}
