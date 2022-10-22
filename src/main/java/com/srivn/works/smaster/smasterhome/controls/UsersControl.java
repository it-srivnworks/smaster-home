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

import com.srivn.works.smaster.smasterhome.SmasterHomeApplication;
import com.srivn.works.smaster.smasterhome.model.users.UserInfo;
import com.srivn.works.smaster.smasterhome.services.UsersService;

@RestController
@RequestMapping("users")
public class UsersControl {

	
	@Autowired
	UsersService usersService;
	
	private static final Logger logger = LoggerFactory.getLogger(UsersControl.class);
	
	@PostMapping(value = "/addNewUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewUser(@RequestBody UserInfo userInfo) {
		return new ResponseEntity<>(usersService.addNewUser(userInfo), HttpStatus.OK);
	}

	@GetMapping(value = "/getAllUserInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUserInfo() {
		logger.info("getAllUserInfo");
		return new ResponseEntity<>(usersService.getAllUserInfo(), HttpStatus.OK);
	}

	@GetMapping(value = "/getUserByEmail", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUserByEmail(@RequestParam String userEmail) {
		return new ResponseEntity<>(usersService.getUserByEmail(userEmail), HttpStatus.OK);
	}

	@GetMapping(value = "/getEmail", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmail(@RequestParam String userEmail) {
		System.out.println(userEmail);
		return new ResponseEntity<>(usersService.getEmail(userEmail), HttpStatus.OK);
	}
}
