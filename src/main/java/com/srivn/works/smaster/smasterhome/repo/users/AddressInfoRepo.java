package com.srivn.works.smaster.smasterhome.repo.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srivn.works.smaster.smasterhome.repo.entity.users.AddressInfoEn;

@Repository
public interface AddressInfoRepo extends JpaRepository<AddressInfoEn, Integer>{

}
