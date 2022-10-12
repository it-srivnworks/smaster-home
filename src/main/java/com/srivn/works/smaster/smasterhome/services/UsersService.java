package com.srivn.works.smaster.smasterhome.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srivn.works.smaster.smasterhome.exception.DataNotFoundException;
import com.srivn.works.smaster.smasterhome.exception.DuplicateDataException;
import com.srivn.works.smaster.smasterhome.model.UserInfo;
import com.srivn.works.smaster.smasterhome.repo.UserRepository;
import com.srivn.works.smaster.smasterhome.repo.entity.UserInfoEn;

@Service
public class UsersService {

	@Autowired
	UserRepository userRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public String addNewUser(UserInfo userInfo) {

		if (userRepository.findByUserEmail(userInfo.getUserEmail()).isEmpty()) {
			UserInfoEn en = modelMapper.map(userInfo, UserInfoEn.class);
			userRepository.save(en);
			return "SUCCESS : User created!";
		} else {
			throw new DuplicateDataException("The field already exist !");
		}

	}

	
	public List<UserInfo> getAllUserInfo() {
		List<UserInfoEn> userInfoList = userRepository.findAll();
		if(!userInfoList.isEmpty()) {
			return userInfoList.stream().map(x -> modelMapper.map(x, UserInfo.class)).collect(Collectors.toList());
		}else {
			throw new DataNotFoundException("No record Found!");
		}
	}

	public UserInfo getUserByEmail(String userEmail) {
		Optional<UserInfoEn> userInfoEn = userRepository.findByUserEmail(userEmail);
		if(!userInfoEn.isEmpty()) {
			return modelMapper.map(userInfoEn.get(), UserInfo.class);
		}else {
			throw new DataNotFoundException("No record Found!");
		}
	}

}
