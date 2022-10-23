package com.srivn.works.smaster.smasterhome.repo.util;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srivn.works.smaster.smasterhome.repo.entity.util.ClsnEn;

@Repository
public interface ClsnRepo extends JpaRepository<ClsnEn, Integer>{

	@Cacheable("Clsn")
	ClsnEn findByKey(String key);
}
