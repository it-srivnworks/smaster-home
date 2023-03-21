package com.srivn.works.smaster.smasterhome.model.users;

import java.sql.Date;

import com.srivn.works.smaster.smasterhome.utils.AppConstants;

import lombok.*;

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

	private String primGuardianEmail;
	private String secnGuardianEmail;
	
	@Override
	public int getUserType() {
		// TODO Auto-generated method stub
		return AppConstants.USERTYPE_STUDENT;
	}

}
