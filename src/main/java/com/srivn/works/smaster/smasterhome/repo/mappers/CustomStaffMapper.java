package com.srivn.works.smaster.smasterhome.repo.mappers;

import com.srivn.works.smaster.smasterhome.model.users.StaffInfo;
import com.srivn.works.smaster.smasterhome.model.users.StudentInfo;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StaffInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StudentInfoEn;
import com.srivn.works.smaster.smasterhome.repo.users.GuardianInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.util.ClsnValRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomStaffMapper {

    @Autowired
    ClsnValRepo clsnValRepo;

    public void updateEnFromDTO(StaffInfo staffInfo, StaffInfoEn staffInfoEn){
    if(staffInfoEn.getPrimaryAddress().getCountry()== null || !staffInfo.getPrimaryAddress().getCountry().equals(staffInfoEn.getPrimaryAddress().getCountry().getValue())){
        staffInfoEn.getPrimaryAddress().setCountry(clsnValRepo.findByValue(staffInfo.getPrimaryAddress().getCountry()));
        }
    if(staffInfoEn.getProfileTitle() == null || !staffInfo.getProfileTitle().equals(staffInfoEn.getProfileTitle().getValue())){
        staffInfoEn.setProfileTitle(clsnValRepo.findByValue(staffInfo.getProfileTitle()));
    }
        if(staffInfoEn.getDept() == null || !staffInfo.getDept().equals(staffInfoEn.getDept().getValue())){
            staffInfoEn.setDept(clsnValRepo.findByValue(staffInfo.getDept()));
        }
}

}
