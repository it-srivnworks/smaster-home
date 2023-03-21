package com.srivn.works.smaster.smasterhome.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SmasterKV {

    private int key;
    private String value;
}
