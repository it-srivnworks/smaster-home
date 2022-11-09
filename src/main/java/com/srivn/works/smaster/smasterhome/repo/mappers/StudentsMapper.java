package com.srivn.works.smaster.smasterhome.repo.mappers;

import com.srivn.works.smaster.smasterhome.model.users.StudentInfo;
import com.srivn.works.smaster.smasterhome.model.users.UserInfo;
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
public interface StudentsMapper {


    @Mapping(source = "dob", target = "dob", dateFormat = "dd-MMM-yyyy")
    @Mapping(source = "inDate", target = "inDate", dateFormat = "dd-MMM-yyyy")
    @Mapping(source = "outDate", target = "outDate", dateFormat = "dd-MMM-yyyy")
    @Mapping(source = "primaryAddress.country", target = "primaryAddress.country", qualifiedByName = "CVTOCountry")
    @Mapping(source = "pguardian", target = "primGuardianEmail", qualifiedByName = "UserToEmail")
    @Mapping(source = "sguardian", target = "secnGuardianEmail", qualifiedByName = "UserToEmail")
    StudentInfo EnToDTO(StudentInfoEn studentInfoEn);


    @Mapping(source = "dob", target = "dob", dateFormat = "dd-MMM-yyyy")
    @Mapping(source = "inDate", target = "inDate", dateFormat = "dd-MMM-yyyy")
    @Mapping(source = "outDate", target = "outDate", dateFormat = "dd-MMM-yyyy")
    @Mapping(target = "userID", ignore = true)
    @Mapping(target = "primaryAddress.country", ignore = true)
    @Mapping(target = "pguardian", ignore = true)
    @Mapping(target = "sguardian", ignore = true)
    void updateEnFromDTO(StudentInfo studentInfo,@MappingTarget StudentInfoEn studentInfoEn);


    StudentInfoEn UserRegDTOToEn(UserRegistration userRegistration);

    @Named("CVTOCountry")
    public static String CVTOCountry(ClsnValEn cv) {
        if(cv == null){
            return "";
        }else{
            return cv.getValue();
        }
    }
    @Named("UserToEmail")
    public static String UserToEmail(UserInfoEn ui) {
        if(ui == null){
            return "";
        }else{
            return ui.getUserEmail();
        }

    }

}
