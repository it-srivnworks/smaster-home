package com.srivn.works.smaster.smasterhome.services;

import com.srivn.works.smaster.smasterhome.exception.DuplicateDataException;
import com.srivn.works.smaster.smasterhome.model.SmasterMsg;
import com.srivn.works.smaster.smasterhome.model.users.StaffInfo;
import com.srivn.works.smaster.smasterhome.model.users.UserInfo;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StaffInfoEn;
import com.srivn.works.smaster.smasterhome.repo.mappers.CustomStaffMapper;
import com.srivn.works.smaster.smasterhome.repo.mappers.StaffMapper;
import com.srivn.works.smaster.smasterhome.repo.users.StaffInfoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffService extends UsersService{

    private static final Logger logger = LoggerFactory.getLogger(StaffService.class);

    @Autowired
    StaffInfoRepo staffInfoRepo;


    @Autowired
    CustomStaffMapper customStaffMapper;

    public SmasterMsg updateStaffData(StaffInfo staffInfo) {
        logger.info("updateStaffData()");
        StaffInfoEn staffInfoEn = mapStaffInfoDTO2En(staffInfo);
        staffInfoRepo.save(staffInfoEn);
        return SmasterMsg.builder().statusCode(HttpStatus.OK.value()).message("SUCCESS : User Data Updated!").build();
    }

    public List<StaffInfo> getAllStaffDetials() {
        logger.info("getAllStaffDetials()");
        List<StaffInfoEn> staffInfoEnList = staffInfoRepo.findAll();
        List<StaffInfo> staffDetailList = staffInfoEnList.stream().map(userEn -> staffMapper.EnToDTO(userEn))
                .collect(Collectors.toList());
        return staffDetailList;
    }

    private StaffInfoEn mapStaffInfoDTO2En(StaffInfo staffInfo) {
        StaffInfoEn staffInfoEn = staffInfoRepo.findByUserEmail(staffInfo.getUserEmail()).get();
        if(staffInfoEn != null){
            staffMapper.updateEnFromDTO(staffInfo,staffInfoEn);
            customStaffMapper.updateEnFromDTO(staffInfo,staffInfoEn);
            return staffInfoEn;
        }else{
            throw new DuplicateDataException("The User does not exist !");
        }
    }


}
