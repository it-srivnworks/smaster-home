package com.srivn.works.smaster.smasterhome.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srivn.works.smaster.smasterhome.exception.DataNotFoundException;
import com.srivn.works.smaster.smasterhome.exception.DuplicateDataException;
import com.srivn.works.smaster.smasterhome.model.SmasterMsg;
import com.srivn.works.smaster.smasterhome.model.UserInfo;
import com.srivn.works.smaster.smasterhome.repo.UserRepository;
import com.srivn.works.smaster.smasterhome.repo.entity.UserInfoEn;

@Service
public class UsersService {

	@Autowired
	UserRepository userRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public SmasterMsg addNewUser(UserInfo userInfo) {

		if (userRepository.findByUserEmail(userInfo.getUserEmail()).isEmpty()) {
			UserInfoEn en = modelMapper.map(userInfo, UserInfoEn.class);
			userRepository.save(en);
			return SmasterMsg.builder().statusCode(HttpStatus.ACCEPTED.value()).message("SUCCESS : User created!")
					.build();
		} else {
			throw new DuplicateDataException("The field already exist !");
		}

	}

	public SmasterMsg getAllUserInfo() {
		List<UserInfoEn> userInfoEnList = userRepository.findAll();
		if (!userInfoEnList.isEmpty()) {
			List<UserInfo> userInfoList = new ArrayList<UserInfo>();
			userInfoList = userInfoEnList.stream().map(x -> modelMapper.map(x, UserInfo.class))
					.collect(Collectors.toList());
			return SmasterMsg.builder().statusCode(HttpStatus.OK.value()).message("Received Data")
					.data(userInfoList).build();

		} else {
			throw new DataNotFoundException("No record Found!");
		}
	}

	public UserInfo getUserByEmail(String userEmail) {
		Optional<UserInfoEn> userInfoEn = userRepository.findByUserEmail(userEmail);
		if (!userInfoEn.isEmpty()) {
			return modelMapper.map(userInfoEn.get(), UserInfo.class);
		} else {
			throw new DataNotFoundException("No record Found!");
		}
	}

	public SmasterMsg getEmail(String userEmail) {
		Optional<UserInfoEn> userInfoEn = userRepository.findByUserEmail(userEmail);
		if (!userInfoEn.isEmpty()) {
			return SmasterMsg.builder().statusCode(HttpStatus.IM_USED.value()).message("Email Already Exist !").build();
		} else {
			return SmasterMsg.builder().statusCode(HttpStatus.OK.value()).message("Email available !").build();
		}
	}
}
