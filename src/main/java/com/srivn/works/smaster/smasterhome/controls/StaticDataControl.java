package com.srivn.works.smaster.smasterhome.controls;

import com.srivn.works.smaster.smasterhome.services.StaticDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("data")
public class StaticDataControl {

    @Autowired
    StaticDataService staticDataService;

    private static final Logger logger = LoggerFactory.getLogger(StaticDataControl.class);

    @GetMapping(value = "/getKVRefData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getKVRefData(@RequestParam String reqKey) {
        return new ResponseEntity<>(staticDataService.getListClsnVal(reqKey), HttpStatus.OK);
    }
}
