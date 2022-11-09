package com.srivn.works.smaster.smasterhome.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.srivn.works.smaster.smasterhome.repo.mappers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.srivn.works.smaster.smasterhome.exception.DataNotFoundException;
import com.srivn.works.smaster.smasterhome.exception.DuplicateDataException;
import com.srivn.works.smaster.smasterhome.model.SmasterMsg;
import com.srivn.works.smaster.smasterhome.model.users.GuardianInfo;
import com.srivn.works.smaster.smasterhome.model.users.StaffInfo;
import com.srivn.works.smaster.smasterhome.model.users.StudentInfo;
import com.srivn.works.smaster.smasterhome.model.users.UserInfo;
import com.srivn.works.smaster.smasterhome.model.users.UserRegistration;
import com.srivn.works.smaster.smasterhome.repo.entity.users.GuardianInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StaffInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StudentInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.UserInfoEn;
import com.srivn.works.smaster.smasterhome.repo.users.GuardianInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.users.StaffInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.users.StudentInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.users.UserInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.util.ClsnValRepo;
import com.srivn.works.smaster.smasterhome.utils.AppConstants;

@Service
public class UsersService {

	private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

	@Autowired
	UserInfoRepo userInfoRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	StaffMapper staffMapper;
	@Autowired
	StudentsMapper studentsMapper;
	@Autowired
	GuardianMapper guardianMapper;




	public SmasterMsg addNewUser(UserRegistration userInfo) {
		logger.info("addNewUser()");
		if (userInfoRepo.findByUserEmail(userInfo.getUserEmail()).isEmpty()) {
			addNewUserByType(userInfo);
			return SmasterMsg.builder().statusCode(HttpStatus.OK.value()).message("SUCCESS : User created!").build();
		} else {
			throw new DuplicateDataException("The field already exist !");
		}

	}

	private void addNewUserByType(UserRegistration userInfo) {
		userInfo.setUserPassword(passwordEncoder.encode(userInfo.getUserPassword()));
		switch (userInfo.getUserType()) {
			case AppConstants.USERTYPE_STAFF:
				StaffInfoEn staffInfoEn = staffMapper.UserRegDTOToEn(userInfo);
				userInfoRepo.save(staffInfoEn);
				break;
			case AppConstants.USERTYPE_STUDENT:
				StudentInfoEn studentInfoEn = studentsMapper.UserRegDTOToEn(userInfo);
				userInfoRepo.save(studentInfoEn);
				break;
			case AppConstants.USERTYPE_GUARDIAN:
				GuardianInfoEn guardianInfoEn = guardianMapper.UserRegDTOToEn(userInfo);
				userInfoRepo.save(guardianInfoEn);
				break;
			default:
				throw new DataNotFoundException("No record Found !");
		}
	}

	public SmasterMsg checkUserByEmail(String userEmail) {
		Optional<UserInfoEn> userInfoEn = userInfoRepo.findByUserEmail(userEmail);
		if (!userInfoEn.isEmpty()) {
			return SmasterMsg.builder().statusCode(HttpStatus.IM_USED.value()).message("Email Already Exist !").build();
		} else {
			return SmasterMsg.builder().statusCode(HttpStatus.NOT_FOUND.value()).message("Email Doesnot Exist !")
					.build();
		}
	}

	public List<UserInfo> getAllUserInfo(int userType) {
		List<UserInfoEn> userInfoEnList = userInfoRepo.findAll();
		List<UserInfo> userInfoList = userInfoEnList.stream().map(userEn -> {
			UserInfo userInfo = new UserInfo();
			BeanUtils.copyProperties(userEn,userInfo);
			return userInfo;
		})
				.collect(Collectors.toList());
		return userInfoList;
	}


}
