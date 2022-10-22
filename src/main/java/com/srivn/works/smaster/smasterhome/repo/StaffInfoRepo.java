package com.srivn.works.smaster.smasterhome.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StaffInfoEn;

@Repository
public interface StaffInfoRepo extends JpaRepository<StaffInfoEn, Integer>{

}
