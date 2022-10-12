package com.srivn.works.smaster.smasterhome.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srivn.works.smaster.smasterhome.repo.entity.UserInfoEn;

@Repository
public interface UserRepository extends JpaRepository<UserInfoEn, Integer>{
	Optional<UserInfoEn> findByUserEmail(String userEmail);
	
	
}
