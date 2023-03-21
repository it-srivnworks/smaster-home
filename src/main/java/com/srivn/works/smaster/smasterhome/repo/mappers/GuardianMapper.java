package com.srivn.works.smaster.smasterhome.repo.mappers;

import com.srivn.works.smaster.smasterhome.model.users.GuardianInfo;
import com.srivn.works.smaster.smasterhome.model.users.StaffInfo;
import com.srivn.works.smaster.smasterhome.model.users.UserRegistration;
import com.srivn.works.smaster.smasterhome.repo.entity.users.GuardianInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StaffInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.util.ClsnValEn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface GuardianMapper {

    @Mapping(source = "primaryAddress.country", target = "primaryAddress.country", qualifiedByName = "CVTOString")
    @Mapping(source = "guardianType", target = "category", qualifiedByName = "CVTOString")
    GuardianInfo EnToDTO(GuardianInfoEn guardianInfoEn);

    @Mapping(target = "primaryAddress.country", ignore = true)
    @Mapping(target = "guardianType", ignore = true)
    void updateEnFromDTO(GuardianInfo guardianInfo, @MappingTarget GuardianInfoEn guardianInfoEn);


    GuardianInfoEn UserRegDTOToEn(UserRegistration userRegistration);

    @Named("CVTOString")
    public static String CVTOString(ClsnValEn cv) {
        if(cv == null){
            return "";
        }else{
            return cv.getValue();
        }
    }
}
