package com.srivn.works.smaster.smasterhome.model.users;

import java.sql.Date;

import com.srivn.works.smaster.smasterhome.utils.AppConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfo extends UserInfo{

	private String dob;
	private int picId;
	private String mobile;
	private AddressInfo primaryAddress;
	private String inDate;
	private String outDate;
	
	private String pGuardianEmail;
	private String sGuardianEmail;
	
	public StudentInfo(String title, String firstName, String lastName, String userEmail, int userType) {
		super(title, firstName, lastName, userEmail, AppConstants.USERTYPE_STUDENT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getUserType() {
		// TODO Auto-generated method stub
		return AppConstants.USERTYPE_STUDENT;
	}

}
