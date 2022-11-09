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

    @GetMapping(value = "/getAllStudentDetials", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllStudentDetials() {
        return new ResponseEntity<>(studentsService.getAllStudentDetials(), HttpStatus.OK);
    }

    @GetMapping(value = "/getStudentDetials", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudentDetials(@RequestParam String userEmail) throws InterruptedException {
        Thread.sleep(4000);
        return new ResponseEntity<>(studentsService.getStudentDetials(userEmail), HttpStatus.OK);
    }
}
