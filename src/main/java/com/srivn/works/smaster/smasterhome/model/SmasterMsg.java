package com.srivn.works.smaster.smasterhome.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SmasterMsg {

	private int statusCode;
	private String message;
	private List<?> data;

}
