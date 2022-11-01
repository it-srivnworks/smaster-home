package com.srivn.works.smaster.smasterhome.services;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.srivn.works.smaster.smasterhome.repo.mappers.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
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
	StaffInfoRepo staffInfoRepo;

	@Autowired
	StudentInfoRepo studentInfoRepo;

	@Autowired
	GuardianInfoRepo guardianInfoRepo;

	@Autowired
	ClsnValRepo clsnValRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private StudentsMapper studentsMapper;
	@Autowired
	CustomStudentMapper customStudentMapper;
	@Autowired
	private StaffMapper staffMapper;
	@Autowired
	CustomStaffMapper customStaffMapper;
	@Autowired
	private GuardianMapper guardianMapper;
	@Autowired
	CustomGuardianMapper customGuardianMapper;

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

	public SmasterMsg updateNewUsertData(UserInfo userInfo) {
		logger.info("updateNewUsertData()");
		Optional<Integer> userID = userInfoRepo.getUserIDByUserEmail(userInfo.getUserEmail());
		if (userID.isPresent() &&  userID.get() > 0) {
			switch (userInfo.getUserType()) {
			case AppConstants.USERTYPE_STAFF:
				StaffInfo staffInfo = (StaffInfo)userInfo;
				StaffInfoEn staffInfoEn = mapStaffInfoDTO2En(staffInfo);
				staffInfoRepo.save(staffInfoEn);
				break;
			case AppConstants.USERTYPE_STUDENT:
				StudentInfo studentInfo = (StudentInfo)userInfo;
				StudentInfoEn studentInfoEn = mapStudentInfoDTO2En(studentInfo);
				studentInfoRepo.save(studentInfoEn);
				break;
			case AppConstants.USERTYPE_GUARDIAN:
				GuardianInfo guardianInfo = (GuardianInfo)userInfo;
				GuardianInfoEn guardianInfoEn = mapGuardianInfoDTO2En(guardianInfo, userID.get());
				guardianInfoRepo.save(guardianInfoEn);
				break;
			default:
				throw new DataNotFoundException("No record Found !");
			}
		} else {
			throw new DuplicateDataException("The User doesnot exist !");
		}
		return SmasterMsg.builder().statusCode(HttpStatus.OK.value()).message("SUCCESS : User Data Updated!").build();

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

	public List<StaffInfo> getAllStaffDetials() {
		List<StaffInfoEn> staffInfoEnList = staffInfoRepo.findAll();
		List<StaffInfo> staffDetailList = staffInfoEnList.stream().map(userEn -> staffMapper.EnToDTO(userEn))
				.collect(Collectors.toList());
		return staffDetailList;
	}

	public List<StudentInfo> getAllStudentDetials() {
		List<StudentInfoEn> studentInfoEnList = studentInfoRepo.findAll();
		List<StudentInfo> studentDetailList = studentInfoEnList.stream()
				.map(userEn -> studentsMapper.EnToDTO(userEn)).collect(Collectors.toList());
		return studentDetailList;
	}

	private StaffInfoEn mapStaffInfoDTO2En(StaffInfo staffInfo) {
		StaffInfoEn staffInfoEn = staffInfoRepo.findByUserEmail(staffInfo.getUserEmail()).get();
		staffMapper.updateEnFromDTO(staffInfo,staffInfoEn);
		customStaffMapper.updateEnFromDTO(staffInfo,staffInfoEn);
		return staffInfoEn;
	}

	private StudentInfoEn mapStudentInfoDTO2En(StudentInfo studentInfo) {
		StudentInfoEn studentInfoEn = studentInfoRepo.findByUserEmail(studentInfo.getUserEmail()).get();
		studentsMapper.updateEnFromDTO(studentInfo,studentInfoEn);
		customStudentMapper.updateEnFromDTO(studentInfo,studentInfoEn);
		return studentInfoEn;
	}

	private GuardianInfoEn mapGuardianInfoDTO2En(GuardianInfo guardianInfo, int userID) {
		GuardianInfoEn guardianInfoEn = guardianInfoRepo.findByUserEmail(guardianInfo.getUserEmail()).get();
		guardianMapper.updateEnFromDTO(guardianInfo,guardianInfoEn);
		customGuardianMapper.updateEnFromDTO(guardianInfo,guardianInfoEn);
		return guardianInfoEn;
	}

	private static Converter<Date, String> convertDate2String() {
		Converter<Date, String> dateToString = new Converter<Date, String>() {
			public String convert(MappingContext<Date, String> context) {
				return context.getSource() == null ? null : context.getSource().toString();
			}
		};
		return dateToString;
	}
}
