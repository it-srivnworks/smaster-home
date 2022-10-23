package com.srivn.works.smaster.smasterhome.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.srivn.works.smaster.smasterhome.exception.DataNotFoundException;
import com.srivn.works.smaster.smasterhome.exception.DuplicateDataException;
import com.srivn.works.smaster.smasterhome.exception.SmasterException;
import com.srivn.works.smaster.smasterhome.model.users.UserInfo;
import com.srivn.works.smaster.smasterhome.repo.users.StaffInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.users.UserInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.users.UserRepository;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StaffInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.UserInfoEn;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class UsersServiceTest {

	@Mock
	StaffInfoRepo staffInfoRepo;
	
	@InjectMocks
	UsersService usersService;
	
	private UserInfo sampleDTONew;
	private StaffInfoEn staffInfoEn;
	@BeforeEach
	public void setup() {
		sampleDTONew = UserInfo.builder().title("Mr").firstName("Fname").lastName("LName").userEmail("email").build();

	}
	/*
	@InjectMocks
	UsersService usersService;

	@Mock
	UserRepository userRepository;

	private UserInfoEn sampleEN;
	private UserInfo sampleDTONew;
	private UserInfo sampleDTODup;

	@BeforeEach
	public void setup() {
		sampleEN = UserInfoEn.builder().userName("UName").userEmail("test@srivn.com").userAge(10).build();
		sampleDTODup = UserInfo.builder().userName("UName").userEmail("test@srivn.com").userAge(10).build();

		sampleDTONew = UserInfo.builder().userName("UName02").userEmail("test02@srivn.com").userAge(20).build();

	}

	// precondition or setup
	// action or the behaviour that we are going test
	// verify the output

	@DisplayName("Test Adding new Unique Entry")
	@Test
	final void test_AddNewUser_New() {
		// precondition or setup
		when(userRepository.findByUserEmail(sampleDTONew.getUserEmail())).thenReturn(Optional.empty());
		when(userRepository.save(sampleEN)).thenReturn(sampleEN);

		// action or the behaviour that we are going test
		assertDoesNotThrow(() -> usersService.addNewUser(sampleDTONew));
	}

	@DisplayName("Test Adding Entry with exisitng email")
	@Test
	final void test_AddNewUser_Dup() {
		// precondition or setup
		when(userRepository.findByUserEmail(sampleDTODup.getUserEmail())).thenReturn(Optional.of(sampleEN));
		when(userRepository.save(sampleEN)).thenReturn(sampleEN);

		// action or the behaviour that we are going test
		assertThrows(DuplicateDataException.class, () -> usersService.addNewUser(sampleDTODup));
	}

	@DisplayName("Test Getting All users List")
	@Test
	final void test_GetAllUserInfo_YES() {
		when(userRepository.findAll()).thenReturn(List.of(sampleEN));
		assertNotNull(usersService.getAllUserInfo());
	}

	@DisplayName("Test Getting All empty users List")
	@Test
	final void test_GetAllUserInfo_EMTPY() {
		when(userRepository.findAll()).thenReturn(Collections.emptyList());
		assertThrows(DataNotFoundException.class, () -> usersService.getAllUserInfo());
	}

	@DisplayName("Test Getting user by email")
	@Test
	final void test_GetUserByEmail_YES() {
		when(userRepository.findByUserEmail(sampleDTODup.getUserEmail())).thenReturn(Optional.of(sampleEN));

		UserInfo userInfo = usersService.getUserByEmail(sampleDTODup.getUserEmail());
		assertEquals(sampleEN.getUserEmail(), userInfo.getUserEmail());
	}

	@DisplayName("Test Getting No Data for user by email")
	@Test
	final void test_GetUserByEmail_NO() {
		when(userRepository.findByUserEmail(sampleDTODup.getUserEmail())).thenReturn(Optional.empty());
		assertThrows(DataNotFoundException.class, () -> usersService.getUserByEmail(sampleDTODup.getUserEmail()));
	}
	*/
	@DisplayName("Test Adding Basic User Info")
	@Test
	final void test_addNewUser() {
		usersService.addNewUser(sampleDTONew);
	}
	
}
