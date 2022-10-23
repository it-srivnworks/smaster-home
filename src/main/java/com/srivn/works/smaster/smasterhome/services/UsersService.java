package com.srivn.works.smaster.smasterhome.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.srivn.works.smaster.smasterhome.exception.DuplicateDataException;
import com.srivn.works.smaster.smasterhome.model.SmasterMsg;
import com.srivn.works.smaster.smasterhome.model.users.UserInfo;
import com.srivn.works.smaster.smasterhome.repo.entity.users.AddressInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StaffInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.UserInfoEn;
import com.srivn.works.smaster.smasterhome.repo.users.AddressInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.users.StaffInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.users.UserInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.util.ClsnValRepo;

@Service
public class UsersService {

	private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

	@Autowired
	UserInfoRepo userInfoRepo;
	
	@Autowired
	StaffInfoRepo staffInfoRepo;

	
	
	private ModelMapper modelMapper = new ModelMapper();

	public SmasterMsg addNewUser(UserInfo userInfo) {
		logger.info("addNewUser()");
		if (userInfoRepo.findByUserEmail(userInfo.getUserEmail()).isEmpty()) {
			StaffInfoEn staffInfoEn = modelMapper.map(userInfo, StaffInfoEn.class);
			staffInfoRepo.save(staffInfoEn);
			return SmasterMsg.builder().statusCode(HttpStatus.OK.value()).message("SUCCESS : User created!").build();
		} else {
			throw new DuplicateDataException("The field already exist !");
		}

	}

	public SmasterMsg checkUserByEmail(String userEmail) {
		Optional<UserInfoEn> staffInfoEn = userInfoRepo.findByUserEmail(userEmail);
		if (!staffInfoEn.isEmpty()) {
			return SmasterMsg.builder().statusCode(HttpStatus.IM_USED.value()).message("Email Already Exist !").build();
		} else {
			return SmasterMsg.builder().statusCode(HttpStatus.NOT_FOUND.value()).message("Email Donot Exist !").build();
		}
	}
}
