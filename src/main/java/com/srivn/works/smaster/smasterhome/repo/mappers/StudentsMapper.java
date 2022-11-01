package com.srivn.works.smaster.smasterhome.repo.mappers;

import com.srivn.works.smaster.smasterhome.model.users.StudentInfo;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StudentInfoEn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentsMapper {

    /*@Mapping(source = "dob", target = "dob", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "inDate", target = "inDate", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "outDate", target = "outDate", dateFormat = "dd.MM.yyyy")
    @Mapping(target = "primaryAddress.country", ignore = true)
    @Mapping(target = "userID", ignore = true)
    StudentInfoEn DTOTOEn(StudentInfo studentInfo);*/

    @Mapping(source = "dob", target = "dob", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "inDate", target = "inDate", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "outDate", target = "outDate", dateFormat = "dd.MM.yyyy")
    @Mapping(target = "primaryAddress.country", ignore = true)
    @Mapping(target = "userID", ignore = true)
    void updateEnFromDTO(StudentInfo studentInfo,@MappingTarget StudentInfoEn studentInfoEn);

}
