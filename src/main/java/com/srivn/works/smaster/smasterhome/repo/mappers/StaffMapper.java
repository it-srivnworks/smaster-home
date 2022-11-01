package com.srivn.works.smaster.smasterhome.repo.mappers;

import com.srivn.works.smaster.smasterhome.model.users.StaffInfo;
import com.srivn.works.smaster.smasterhome.model.users.StudentInfo;
import com.srivn.works.smaster.smasterhome.model.users.UserRegistration;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StaffInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StudentInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.UserInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.util.ClsnValEn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StaffMapper {


    @Mapping(source = "dob", target = "dob", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "inDate", target = "inDate", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "outDate", target = "outDate", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "primaryAddress.country", target = "primaryAddress.country", qualifiedByName = "CVTOString")
    @Mapping(source = "profileTitle", target = "profileTitle", qualifiedByName = "CVTOString")
    @Mapping(source = "dept", target = "dept", qualifiedByName = "CVTOString")
    StaffInfo EnToDTO(StaffInfoEn staffInfoEn);


    @Mapping(source = "dob", target = "dob", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "inDate", target = "inDate", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "outDate", target = "outDate", dateFormat = "dd.MM.yyyy")
    @Mapping(target = "userID", ignore = true)
    @Mapping(target = "primaryAddress.country", ignore = true)
    @Mapping(target = "profileTitle", ignore = true)
    @Mapping(target = "dept", ignore = true)
    void updateEnFromDTO(StaffInfo staffInfo, @MappingTarget StaffInfoEn staffInfoEn);


    StaffInfoEn UserRegDTOToEn(UserRegistration userRegistration);

    @Named("CVTOString")
    public static String CVTOString(ClsnValEn cv) {
        if(cv == null){
            return "";
        }else{
            return cv.getValue();
        }
    }
}
