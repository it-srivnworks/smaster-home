package com.srivn.works.smaster.smasterhome.controls;

import com.srivn.works.smaster.smasterhome.model.users.StaffInfo;
import com.srivn.works.smaster.smasterhome.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/staff")
public class StaffControl {

    @Autowired
    StaffService staffService;

    @PostMapping(value = "/updateNewStaffData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNewStaffData(@RequestBody StaffInfo staffInfo) {
        return new ResponseEntity<>(staffService.updateStaffData(staffInfo), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllStaffDetials", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllStaffDetials() {
        return new ResponseEntity<>(staffService.getAllStaffDetials(), HttpStatus.OK);
    }
}
