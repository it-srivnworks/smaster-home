package com.srivn.works.smaster.smasterhome.repo.mappers;

import com.srivn.works.smaster.smasterhome.model.users.StudentInfo;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StudentInfoEn;
import com.srivn.works.smaster.smasterhome.repo.users.GuardianInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.users.StudentInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.util.ClsnValRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomStudentMapper {

    @Autowired
    GuardianInfoRepo guardianInfoRepo;

    @Autowired
    ClsnValRepo clsnValRepo;

    public void updateEnFromDTO(StudentInfo studentInfo, StudentInfoEn studentInfoEn){
    if(studentInfo.getPrimGuardianEmail() != null && (studentInfoEn.getPguardian() == null || !studentInfo.getPrimGuardianEmail().equals(studentInfoEn.getPguardian().getUserEmail()))){
        studentInfoEn.setPguardian(guardianInfoRepo.findByUserEmail(studentInfo.getPrimGuardianEmail()).get());
    }
    if (studentInfo.getSecnGuardianEmail() != null && (studentInfoEn.getSguardian() == null || !studentInfo.getSecnGuardianEmail().equals(studentInfoEn.getSguardian().getUserEmail()))){
        studentInfoEn.setSguardian(guardianInfoRepo.findByUserEmail(studentInfo.getSecnGuardianEmail()).get());
    }
    if(studentInfoEn.getPrimaryAddress().getCountry()== null || !studentInfo.getPrimaryAddress().getCountry().equals(studentInfoEn.getPrimaryAddress().getCountry().getValue())){
        studentInfoEn.getPrimaryAddress().setCountry(clsnValRepo.findByValue(studentInfo.getPrimaryAddress().getCountry()));
        }

    }
}
