package com.srivn.works.smaster.smasterhome.model.users;

import com.srivn.works.smaster.smasterhome.utils.AppConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuardianInfo extends UserInfo{

	private String mobile;
	private AddressInfo primaryAddress;
	
	private String category;
	
	public GuardianInfo(String title, String firstName, String lastName, String userEmail, int userType) {
		super(title, firstName, lastName, userEmail, AppConstants.USERTYPE_GUARDIAN);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getUserType() {
		// TODO Auto-generated method stub
		return AppConstants.USERTYPE_GUARDIAN;
	}
	
}
