package com.srivn.works.smaster.smasterhome.repo.util;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srivn.works.smaster.smasterhome.repo.entity.util.ClsnValEn;

import java.util.List;

@Repository
public interface ClsnValRepo extends JpaRepository<ClsnValEn, Integer>{

	@Cacheable("ClsnVal")
	ClsnValEn findByValue(String value);
	
	@Cacheable("ClsnVal")
	ClsnValEn findByClsnValID(int clsnValID);

}
