package com.srivn.works.smaster.smasterhome.services;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.srivn.works.smaster.smasterhome.exception.DataNotFoundException;
import com.srivn.works.smaster.smasterhome.exception.DuplicateDataException;
import com.srivn.works.smaster.smasterhome.model.SmasterMsg;
import com.srivn.works.smaster.smasterhome.model.users.StaffDetials;
import com.srivn.works.smaster.smasterhome.model.users.UserInfo;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StaffInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StudentInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.UserInfoEn;
import com.srivn.works.smaster.smasterhome.repo.users.StaffInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.users.UserInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.util.ClsnValRepo;
import com.srivn.works.smaster.smasterhome.utils.AppConstants;

@Service
public class UsersService {

	private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

	@Autowired
	UserInfoRepo userInfoRepo;

	@Autowired
	StaffInfoRepo staffInfoRepo;

	@Autowired
	ClsnValRepo clsnValRepo;

	private ModelMapper modelMapper;

	public SmasterMsg addNewUser(UserInfo userInfo) {
		logger.info("addNewUser()");
		if (userInfoRepo.findByUserEmail(userInfo.getUserEmail()).isEmpty()) {
			addNewUserByType(userInfo);
			return SmasterMsg.builder().statusCode(HttpStatus.OK.value()).message("SUCCESS : User created!").build();
		} else {
			throw new DuplicateDataException("The field already exist !");
		}

	}

	public SmasterMsg updateNewStaffData(StaffDetials staffDetials) {
		logger.info("updateNewStaffData()");
		int userID = staffInfoRepo.getUserIDByUserEmail(staffDetials.getUserEmail());
		if (userID > 0) {
			StaffInfoEn staffInfoEnNew = mapStaffInfoDTO2En(staffDetials, userID);
			staffInfoRepo.save(staffInfoEnNew);
			return SmasterMsg.builder().statusCode(HttpStatus.OK.value()).message("SUCCESS : User Data Updated!")
					.build();
		} else {
			throw new DuplicateDataException("The User doesnot exist !");
		}
	}

	public SmasterMsg checkUserByEmail(String userEmail) {
		Optional<UserInfoEn> staffInfoEn = userInfoRepo.findByUserEmail(userEmail);
		if (!staffInfoEn.isEmpty()) {
			return SmasterMsg.builder().statusCode(HttpStatus.IM_USED.value()).message("Email Already Exist !").build();
		} else {
			return SmasterMsg.builder().statusCode(HttpStatus.NOT_FOUND.value()).message("Email Doesnot Exist !")
					.build();
		}
	}

	private void addNewUserByType(UserInfo userInfo) {
		modelMapper = new ModelMapper();
		switch (userInfo.getUserType()) {
		case AppConstants.USERTYPE_STAFF:
			StaffInfoEn staffInfoEn = modelMapper.map(userInfo, StaffInfoEn.class);
			userInfoRepo.save(staffInfoEn);
			break;
		case AppConstants.USERTYPE_STUDENT:
			StudentInfoEn studentInfoEn = modelMapper.map(userInfo, StudentInfoEn.class);
			userInfoRepo.save(studentInfoEn);
			break;
		default:
			throw new DataNotFoundException("No record Found !");
		}
	}

	private StaffInfoEn mapStaffInfoDTO2En(StaffDetials staffDetials, int userID) {
		modelMapper = new ModelMapper();
		StaffInfoEn staffInfoEn = modelMapper.map(staffDetials, StaffInfoEn.class);
		staffInfoEn.setUserID(userID);
		staffInfoEn.setDeptID(clsnValRepo.findByValue(staffDetials.getDept()));
		staffInfoEn.getPrimaryAddress().setCountry(clsnValRepo.findByValue(staffDetials.getPrimaryAddress().getCountry()));
		return staffInfoEn;
	}
}
