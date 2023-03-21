package com.srivn.works.smaster.smasterhome.exception;

import lombok.Data;

@Data
public class SmasterException extends RuntimeException {

	public SmasterException(String msg) {
		super(msg);
		this.message = msg;
	}
	
	private String message;

}
