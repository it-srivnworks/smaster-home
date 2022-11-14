package com.srivn.works.smaster.smasterhome.controls;

import com.srivn.works.smaster.smasterhome.model.users.GuardianInfo;
import com.srivn.works.smaster.smasterhome.model.users.StudentInfo;
import com.srivn.works.smaster.smasterhome.services.StudentsService;
import com.srivn.works.smaster.smasterhome.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("users/students")
public class StudentsControl {

    @Autowired
    StudentsService studentsService;

    @PostMapping(value = "/updateNewStudentData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNewStudentData(@RequestBody StudentInfo studentInfo) {
        return new ResponseEntity<>(studentsService.updateStudentsData(studentInfo), HttpStatus.OK);
    }

    @PostMapping(value = "/updateNewGuardianData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNewuserDetails(@RequestBody GuardianInfo guardianInfo) {
        return new ResponseEntity<>(studentsService.updateGuardianData(guardianInfo), HttpStatus.OK);
    }

    @PostMapping("/uploadProfilePic")
    public ResponseEntity<?> uploadProfilePic(@RequestParam String userEmail, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            studentsService.uploadProfilePic(userEmail, file);
        } else {
            return new ResponseEntity<>("Please select a file to upload", HttpStatus.OK);
        }
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }


    @GetMapping(value = "/getAllStudentDetials", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllStudentDetials() {
        return new ResponseEntity<>(studentsService.getAllStudentDetials(), HttpStatus.OK);
    }

    @GetMapping(value = "/getStudentDetials", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudentDetials(@RequestParam String userEmail) throws InterruptedException {
        Thread.sleep(4000);
        return new ResponseEntity<>(studentsService.getStudentDetials(userEmail), HttpStatus.OK);
    }

    @GetMapping(value = "/getProfilePic", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> getProfilePic(@RequestParam String userEmail) {
        byte[] bytes = studentsService.getProfilePic(userEmail);
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }

}
