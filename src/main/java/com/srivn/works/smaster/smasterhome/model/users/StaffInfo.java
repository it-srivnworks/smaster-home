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

	private String dob;
	private int picId;
	private String mobile;
	private AddressInfo primaryAddress;
	private String inDate;
	private String outDate;
	
	private String profileTitle;
	private String profileDescription;
	private String dept;

	@Override
	public int getUserType() {
		// TODO Auto-generated method stub
		return AppConstants.USERTYPE_STAFF;
	}

}
