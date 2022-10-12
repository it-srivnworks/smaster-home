package com.srivn.works.smaster.smasterhome.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.srivn.works.smaster.smasterhome.repo.entity.UserInfoEn;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	private UserInfoEn en;

	@BeforeEach
	public void setup() {
		en = UserInfoEn.builder().userName("UName").userEmail("test@srivn.com").userAge(10).build();
	}
	
	@DisplayName("Test Adding new User to Repo")
	@Test
	@Order(1)
	void test_User_Repository_add() {
		UserInfoEn res = userRepository.save(en);
		assertNotNull(res);
	}

	@DisplayName("Test finding User from Repo")
	@Test
	@Order(2)
	void test_User_Repository_findByUserEmail() {
		UserInfoEn req = userRepository.save(en);
		Optional<UserInfoEn> res = userRepository.findByUserEmail("test@srivn.com");
		System.out.println(res);
		assertTrue(res.isPresent());
	}
}