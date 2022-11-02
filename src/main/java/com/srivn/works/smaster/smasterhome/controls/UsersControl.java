package com.srivn.works.smaster.smasterhome.controls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srivn.works.smaster.smasterhome.model.users.GuardianInfo;
import com.srivn.works.smaster.smasterhome.model.users.StaffInfo;
import com.srivn.works.smaster.smasterhome.model.users.StudentInfo;
import com.srivn.works.smaster.smasterhome.services.UsersService;

@RestController
@RequestMapping("users")
public class UsersControl {

	
	@Autowired
	UsersService usersService;
	
	private static final Logger logger = LoggerFactory.getLogger(UsersControl.class);
	

	
	@PostMapping(value = "/updateNewStaffData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateNewStaffData(@RequestBody StaffInfo staffInfo) {
		return new ResponseEntity<>(usersService.updateNewUsertData(staffInfo), HttpStatus.OK);
	}
	
	@PostMapping(value = "/updateNewStudentData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateNewStudentData(@RequestBody StudentInfo studentInfo) {
		//return new ResponseEntity<>(usersService.updateNewStudentData(studentInfo), HttpStatus.OK);
		return new ResponseEntity<>(usersService.updateNewUsertData(studentInfo), HttpStatus.OK);
	}
	
	@PostMapping(value = "/updateNewGuardianData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateNewuserDetails(@RequestBody GuardianInfo guardianInfo) {
		usersService.updateNewUsertData(guardianInfo);
		return new ResponseEntity<>(usersService.updateNewUsertData(guardianInfo), HttpStatus.OK);
	}
	
	@GetMapping(value = "/checkUserByEmail", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checkUserByEmail(@RequestParam String userEmail) {
		return new ResponseEntity<>(usersService.checkUserByEmail(userEmail), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllUserInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUserInfo(int userType) {
		return new ResponseEntity<>(usersService.getAllUserInfo(userType), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllStaffDetials", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllStaffDetials() {
		return new ResponseEntity<>(usersService.getAllStaffDetials(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllStudentDetials", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllStudentDetials() {
		return new ResponseEntity<>(usersService.getAllStudentDetials(), HttpStatus.OK);
	}
}
