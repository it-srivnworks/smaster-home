package com.srivn.works.smaster.smasterhome.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.srivn.works.smaster.smasterhome.repo.entity.users.UserInfoEn;
import com.srivn.works.smaster.smasterhome.repo.users.UserInfoRepo;

@Service
public class JWTUserDetailsService implements UserDetailsService {

	@Autowired
	UserInfoRepo userInfoRepo;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

		Optional<UserInfoEn> userInfoEn = userInfoRepo.findByUserEmail(userEmail);
		if (userInfoEn.isPresent()) {
			return new User(userInfoEn.get().getUserEmail(), userInfoEn.get().getUserPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with userEmail: " + userEmail);
		}
	}
}