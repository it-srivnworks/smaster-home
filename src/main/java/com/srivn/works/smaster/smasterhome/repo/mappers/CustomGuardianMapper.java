package com.srivn.works.smaster.smasterhome.repo.mappers;

import com.srivn.works.smaster.smasterhome.model.users.GuardianInfo;
import com.srivn.works.smaster.smasterhome.model.users.StaffInfo;
import com.srivn.works.smaster.smasterhome.repo.entity.users.GuardianInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StaffInfoEn;
import com.srivn.works.smaster.smasterhome.repo.util.ClsnValRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomGuardianMapper {

    @Autowired
    ClsnValRepo clsnValRepo;

    public void updateEnFromDTO(GuardianInfo guardianInfo, GuardianInfoEn guardianInfoEn){
    if(guardianInfoEn.getPrimaryAddress().getCountry()== null || !guardianInfo.getPrimaryAddress().getCountry().equals(guardianInfoEn.getPrimaryAddress().getCountry().getValue())){
        guardianInfoEn.getPrimaryAddress().setCountry(clsnValRepo.findByValue(guardianInfo.getPrimaryAddress().getCountry()));
        }
    if(guardianInfoEn.getGuardianType() == null || !guardianInfo.getCategory().equals(guardianInfoEn.getGuardianType().getValue())){
        guardianInfoEn.setGuardianType(clsnValRepo.findByValue(guardianInfo.getCategory()));
    }

}

}
