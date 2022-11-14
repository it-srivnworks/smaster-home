package com.srivn.works.smaster.smasterhome.services;

import com.srivn.works.smaster.smasterhome.exception.DataNotFoundException;
import com.srivn.works.smaster.smasterhome.exception.DataUpdateException;
import com.srivn.works.smaster.smasterhome.exception.DuplicateDataException;
import com.srivn.works.smaster.smasterhome.model.SmasterMsg;
import com.srivn.works.smaster.smasterhome.model.users.GuardianInfo;
import com.srivn.works.smaster.smasterhome.model.users.StaffInfo;
import com.srivn.works.smaster.smasterhome.model.users.StudentInfo;
import com.srivn.works.smaster.smasterhome.repo.entity.users.GuardianInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StaffInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.StudentInfoEn;
import com.srivn.works.smaster.smasterhome.repo.entity.users.UserPicturesEn;
import com.srivn.works.smaster.smasterhome.repo.mappers.CustomGuardianMapper;
import com.srivn.works.smaster.smasterhome.repo.mappers.CustomStudentMapper;
import com.srivn.works.smaster.smasterhome.repo.mappers.StudentsMapper;
import com.srivn.works.smaster.smasterhome.repo.users.GuardianInfoRepo;
import com.srivn.works.smaster.smasterhome.repo.users.StudentInfoRepo;
import com.srivn.works.smaster.smasterhome.utils.AppConstants;
import com.srivn.works.smaster.smasterhome.utils.AppFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentsService extends UsersService{

    @Autowired
    StudentInfoRepo studentInfoRepo;

    @Autowired
    GuardianInfoRepo guardianInfoRepo;

    @Autowired
    CustomStudentMapper customStudentMapper;
    @Autowired
    CustomGuardianMapper customGuardianMapper;

    @Value("${smaster.home.profilepicpath}")
    private String userBucketPath;

    @Value("${smaster.home.profPicFileSize}")
    private int profPicFileSize;
    private static final Logger logger = LoggerFactory.getLogger(StudentsService.class);

    public SmasterMsg updateStudentsData(StudentInfo studentInfo) {
        logger.info("updateStudentsData()");
        StudentInfoEn studentInfoEn = mapStudentInfoDTO2En(studentInfo);
        studentInfoRepo.save(studentInfoEn);
        return SmasterMsg.builder().statusCode(HttpStatus.OK.value()).message("SUCCESS : User Data Updated!").build();
    }

    public SmasterMsg updateGuardianData(GuardianInfo guardianInfo) {
        logger.info("updateGuardianData()");
        GuardianInfoEn guardianInfoEn = mapGuardianInfoDTO2En(guardianInfo);
        guardianInfoRepo.save(guardianInfoEn);
        return SmasterMsg.builder().statusCode(HttpStatus.OK.value()).message("SUCCESS : User Data Updated!").build();
    }

    public SmasterMsg uploadProfilePic(String userEmail, MultipartFile file){
        if(file!= null && file.getSize() > profPicFileSize){
            return SmasterMsg.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message("Invalid File Size!").build();
        }else{
            StudentInfoEn studentInfoEn = (StudentInfoEn)userInfoRepo.findByUserEmail(userEmail).get();
            if(studentInfoEn.getUserPic() != null){
                String filePath = userBucketPath+AppConstants.IMAGE_APPEND_PROF+studentInfoEn.getUserPic().getId()+ AppConstants.IMAGE_EXT_PNG;
                if(AppFileUtil.saveFile(filePath,file)){
                    studentInfoEn.getUserPic().setFilePath(filePath);
                    studentInfoRepo.save(studentInfoEn);
                }else{
                    throw new DataUpdateException("Issue Saving File !");
                }
            }else{
                UserPicturesEn userPicturesEn = new UserPicturesEn(userBucketPath,0);
                studentInfoEn.setUserPic(userPicturesEn);
                studentInfoEn = studentInfoRepo.save(studentInfoEn);
                String filePath = userBucketPath+AppConstants.IMAGE_APPEND_PROF+studentInfoEn.getUserPic().getId()+ AppConstants.IMAGE_EXT_PNG;
                if(AppFileUtil.saveFile(filePath,file)){
                    studentInfoEn.getUserPic().setFilePath(filePath);
                    studentInfoRepo.save(studentInfoEn);
                }else{
                    throw new DataUpdateException("Issue Saving File !");
                }
            }
            return SmasterMsg.builder().statusCode(HttpStatus.OK.value()).message("SUCCESS : User Data Updated!").build();
        }
    }

    public List<StudentInfo> getAllStudentDetials() {
        List<StudentInfoEn> studentInfoEnList = studentInfoRepo.findAll();
        List<StudentInfo> studentDetailList = studentInfoEnList.stream()
                .map(userEn -> studentsMapper.EnToDTO(userEn)).collect(Collectors.toList());
        return studentDetailList;
    }

    public StudentInfo getStudentDetials(String userEmail) {
        StudentInfoEn studentInfoEn = studentInfoRepo.findByUserEmail(userEmail).get();
        StudentInfo studentInfo = studentsMapper.EnToDTO(studentInfoEn);
        return studentInfo;
    }

    public byte[] getProfilePic(String userEmail) {
        StudentInfoEn studentInfoEn = studentInfoRepo.findByUserEmail(userEmail).get();
        if(studentInfoEn.getUserPic() != null){
            try {
               return AppFileUtil.readFile(studentInfoEn.getUserPic().getFilePath());
            } catch (IOException e) {
                throw new DataNotFoundException("Issue Retreiving Data !");
            }
        }else{
            throw new DataNotFoundException("The Data does not exist !");
        }

    }
    private StudentInfoEn mapStudentInfoDTO2En(StudentInfo studentInfo) {
        StudentInfoEn studentInfoEn = studentInfoRepo.findByUserEmail(studentInfo.getUserEmail()).get();
        if(studentInfoEn != null){
            studentsMapper.updateEnFromDTO(studentInfo,studentInfoEn);
            customStudentMapper.updateEnFromDTO(studentInfo,studentInfoEn);
            return studentInfoEn;
        }else{
            throw new DuplicateDataException("The User does not exist !");
        }

    }

    private GuardianInfoEn mapGuardianInfoDTO2En(GuardianInfo guardianInfo) {
        GuardianInfoEn guardianInfoEn = guardianInfoRepo.findByUserEmail(guardianInfo.getUserEmail()).get();
        if(guardianInfoEn != null){
        guardianMapper.updateEnFromDTO(guardianInfo,guardianInfoEn);
        customGuardianMapper.updateEnFromDTO(guardianInfo,guardianInfoEn);
        return guardianInfoEn;
        }else{
            throw new DuplicateDataException("The User does not exist !");
        }
    }

}
