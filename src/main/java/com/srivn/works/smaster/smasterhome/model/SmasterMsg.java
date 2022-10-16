package com.srivn.works.smaster.smasterhome.model;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SmasterMsg {

	private int statusCode;
	private String message;

}
