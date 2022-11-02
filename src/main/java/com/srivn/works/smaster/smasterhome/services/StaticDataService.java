package com.srivn.works.smaster.smasterhome.services;

import com.srivn.works.smaster.smasterhome.exception.DataNotFoundException;
import com.srivn.works.smaster.smasterhome.model.SmasterKV;
import com.srivn.works.smaster.smasterhome.repo.entity.util.ClsnEn;
import com.srivn.works.smaster.smasterhome.repo.entity.util.ClsnValEn;
import com.srivn.works.smaster.smasterhome.repo.util.ClsnRepo;
import com.srivn.works.smaster.smasterhome.repo.util.ClsnValRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StaticDataService {

    @Autowired
    ClsnRepo clsnRepo;
    @Autowired
    ClsnValRepo clsnValRepo;

    public List<SmasterKV> getListClsnVal(String key){
        ClsnEn keyObj =  clsnRepo.findByKey(key);
        if(keyObj != null){
            List<SmasterKV> cvals = keyObj.getClsnVals().stream().map(cval -> {
                return SmasterKV.builder().key(cval.getClsnValID()).value(cval.getValue()).build();
            }).collect(Collectors.toList());
            return cvals;
        }else{
            throw new DataNotFoundException("Data Donot exist!");
        }
    }
}
