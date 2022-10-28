package com.srivn.works.smaster.smasterhome.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srivn.works.smaster.smasterhome.config.JwtTokenUtil;
import com.srivn.works.smaster.smasterhome.exception.DuplicateDataException;
import com.srivn.works.smaster.smasterhome.exception.NoAccessException;
import com.srivn.works.smaster.smasterhome.exception.SmasterException;
import com.srivn.works.smaster.smasterhome.model.users.JwtResponse;
import com.srivn.works.smaster.smasterhome.model.users.UserLoginData;
import com.srivn.works.smaster.smasterhome.model.users.UserRegistration;
import com.srivn.works.smaster.smasterhome.services.JWTUserDetailsService;
import com.srivn.works.smaster.smasterhome.services.UsersService;

import io.jsonwebtoken.MalformedJwtException;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("useradmin")
public class UserAdminControl {

	@Autowired
	UsersService usersService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JWTUserDetailsService userDetailsService;

	@PostMapping(value = "/addNewUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewUser(@RequestBody UserRegistration userInfo) {
		return new ResponseEntity<>(usersService.addNewUser(userInfo), HttpStatus.OK);
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserLoginData userLoginData) throws Exception {
		authenticate(userLoginData.getUserEmail(), userLoginData.getUserPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginData.getUserEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		JwtResponse jwtt = new JwtResponse(userLoginData.getUserEmail(),token);
		return ResponseEntity.ok(jwtt);
	}

	private void authenticate(String userEmail, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, password));
		} catch (DisabledException e) {
			throw new NoAccessException("USER_DISABLED !");
		} catch (BadCredentialsException e) {
			throw new NoAccessException("INVALID_CREDENTIALS !");
		}
	}

}