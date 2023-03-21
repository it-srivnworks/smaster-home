package com.srivn.works.smaster.smasterhome.repo.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.srivn.works.smaster.smasterhome.repo.entity.users.StaffInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.UserInfoEn;

@Repository
public interface StaffInfoRepo extends JpaRepository<StaffInfoEn, Integer>{

	Optional<StaffInfoEn> findByUserEmail(String userEmail);
	
	@Query("SELECT si.userID FROM StaffInfoEn si WHERE si.userEmail = :userEmail")
	public int getUserIDByUserEmail(@Param("userEmail") String userEmail);
}
