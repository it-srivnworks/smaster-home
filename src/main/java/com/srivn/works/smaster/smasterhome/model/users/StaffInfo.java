package com.srivn.works.smaster.smasterhome.model.users;

import java.sql.Date;

import com.srivn.works.smaster.smasterhome.utils.AppConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffInfo extends UserInfo{

	private Date dob;
	private int picId;
	private String mobile;
	private AddressInfo primaryAddress;
	private Date inDate;
	private Date outDate;
	
	private String profileTitle;
	private String profileDescription;
	private String dept;
	
	public StaffInfo(String title, String firstName, String lastName, String userEmail, int userType) {
		super(title, firstName, lastName, userEmail, AppConstants.USERTYPE_STAFF);
		// TODO Auto-generated constructor stub
	}

		
	@Override
	public int getUserType() {
		// TODO Auto-generated method stub
		return AppConstants.USERTYPE_STAFF;
	}
}
