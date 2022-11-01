package com.srivn.works.smaster.smasterhome.services;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.srivn.works.smaster.smasterhome.repo.mappers.StudentsMapper;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private ModelMapper modelMapper;

	@Autowired
	private StudentsMapper studentsMapper;

	public SmasterMsg addNewUser(UserRegistration userInfo) {
		logger.info("addNewUser()");
		if (userInfoRepo.findByUserEmail(userInfo.getUserEmail()).isEmpty()) {
			addNewUserByType(userInfo);
			return SmasterMsg.builder().statusCode(HttpStatus.OK.value()).message("SUCCESS : User created!").build();
		} else {
			throw new DuplicateDataException("The field already exist !");
		}

	}

	public SmasterMsg updateNewUsertData(UserInfo userInfo) {
		logger.info("updateNewUsertData()");
		Optional<Integer> userID = userInfoRepo.getUserIDByUserEmail(userInfo.getUserEmail());
		if (userID.isPresent() &&  userID.get() > 0) {
			switch (userInfo.getUserType()) {
			case AppConstants.USERTYPE_STAFF:
				StaffInfo staffInfo = (StaffInfo)userInfo;
				StaffInfoEn staffInfoEnNew = mapStaffInfoDTO2En(staffInfo, userID.get());
				staffInfoRepo.save(staffInfoEnNew);
				break;
			case AppConstants.USERTYPE_STUDENT:
				StudentInfo studentInfo = (StudentInfo)userInfo;
				StudentInfoEn studentInfoEn = studentInfoRepo.findByUserEmail(studentInfo.getUserEmail()).get();
				studentsMapper.updateEnFromDTO(studentInfo,studentInfoEn);
				//StudentInfoEn studentInfoEn = mapStudentInfoDTO2En(studentInfo, userID.get());
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
		modelMapper = new ModelMapper();
		List<UserInfoEn> userInfoEnList = userInfoRepo.findAll();
		List<UserInfo> userInfoList = userInfoEnList.stream().map(user -> modelMapper.map(user, UserInfo.class))
				.collect(Collectors.toList());
		return userInfoList;
	}

	public List<StaffInfo> getAllStaffDetials() {
		modelMapper = new ModelMapper();
		List<StaffInfoEn> staffInfoEnList = staffInfoRepo.findAll();
		List<StaffInfo> staffDetailList = staffInfoEnList.stream().map(user -> modelMapper.map(user, StaffInfo.class))
				.collect(Collectors.toList());
		return staffDetailList;
	}

	public List<StudentInfo> getAllStudentDetials() {
		modelMapper = new ModelMapper();
		modelMapper.addConverter(convertDate2String());
		List<StudentInfoEn> studentInfoEnList = studentInfoRepo.findAll();
		List<StudentInfo> studentDetailList = studentInfoEnList.stream()
				.map(user -> modelMapper.map(user, StudentInfo.class)).collect(Collectors.toList());
		return studentDetailList;
	}

	private void addNewUserByType(UserRegistration userInfo) {
		modelMapper = new ModelMapper();
		userInfo.setUserPassword(passwordEncoder.encode(userInfo.getUserPassword()));
		switch (userInfo.getUserType()) {
		case AppConstants.USERTYPE_STAFF:
			StaffInfoEn staffInfoEn = modelMapper.map(userInfo, StaffInfoEn.class);
			userInfoRepo.save(staffInfoEn);
			break;
		case AppConstants.USERTYPE_STUDENT:
			StudentInfoEn studentInfoEn = modelMapper.map(userInfo, StudentInfoEn.class);
			userInfoRepo.save(studentInfoEn);
			break;
		case AppConstants.USERTYPE_GUARDIAN:
			GuardianInfoEn guardianInfoEn = modelMapper.map(userInfo, GuardianInfoEn.class);
			userInfoRepo.save(guardianInfoEn);
			break;
		default:
			throw new DataNotFoundException("No record Found !");
		}
	}

	private StaffInfoEn mapStaffInfoDTO2En(StaffInfo staffInfo, int userID) {
		modelMapper = new ModelMapper();
		StaffInfoEn staffInfoEn = modelMapper.map(staffInfo, StaffInfoEn.class);
		staffInfoEn.setUserID(userID);
		staffInfoEn.setDeptID(clsnValRepo.findByValue(staffInfo.getDept()));
		staffInfoEn.getPrimaryAddress().setCountry(clsnValRepo.findByValue(staffInfo.getPrimaryAddress().getCountry()));
		return staffInfoEn;
	}

	private StudentInfoEn mapStudentInfoDTO2En(StudentInfo studentInfo, int userID) {
		modelMapper = new ModelMapper();
		StudentInfoEn studentInfoEn = modelMapper.map(studentInfo, StudentInfoEn.class);
		studentInfoEn.setUserID(userID);
		studentInfoEn.setPguardian(guardianInfoRepo.findByUserEmail(studentInfo.getPGuardianEmail()).get());
		studentInfoEn.setSguardian(guardianInfoRepo.findByUserEmail(studentInfo.getSGuardianEmail()).get());
		return studentInfoEn;
	}

	private GuardianInfoEn mapGuardianInfoDTO2En(UserInfo guardianInfo, int userID) {
		modelMapper = new ModelMapper();
		GuardianInfoEn guardianInfoEn = modelMapper.map(guardianInfo, GuardianInfoEn.class);
		guardianInfoEn.setUserID(userID);
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
