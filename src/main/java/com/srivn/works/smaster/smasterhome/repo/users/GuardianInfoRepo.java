package com.srivn.works.smaster.smasterhome.repo.users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.srivn.works.smaster.smasterhome.repo.entity.users.GuardianInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StaffInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StudentInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.UserInfoEn;

@Repository
public interface GuardianInfoRepo extends JpaRepository<GuardianInfoEn, Integer>{

	Optional<GuardianInfoEn> findByUserEmail(String userEmail);
	
	@Query("SELECT si.userID FROM GuardianInfoEn si WHERE si.userEmail = :userEmail")
	public int getUserIDByUserEmail(@Param("userEmail") String userEmail);
	
	@Query("SELECT si FROM GuardianInfoEn si WHERE si.userEmail in :userEmailList")
	public List<GuardianInfoEn> getAllGuardianByUserEmail(@Param("userEmailList") List<String> userEmailList);
}
