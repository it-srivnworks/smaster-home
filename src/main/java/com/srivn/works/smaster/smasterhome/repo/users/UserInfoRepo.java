package com.srivn.works.smaster.smasterhome.repo.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.srivn.works.smaster.smasterhome.repo.entity.users.UserInfoEn;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfoEn, Integer>{
	
	public Optional<UserInfoEn> findByUserEmail(String userEmail);
	
	@Query("SELECT ui.userID FROM UserInfoEn ui WHERE ui.userEmail = :userEmail")
	public Optional<Integer> getUserIDByUserEmail(@Param("userEmail") String userEmail);

}
