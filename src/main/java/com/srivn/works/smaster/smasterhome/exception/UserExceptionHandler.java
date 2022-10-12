package com.srivn.works.smaster.smasterhome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler{

	@ExceptionHandler(value = {DataNotFoundException.class})
	public ResponseEntity<ExObject> handleDNFEx(DataNotFoundException ex){
		ExObject exObj = new ExObject();
		exObj.setStatusCode(HttpStatus.NOT_FOUND.value());
		exObj.setMessage(ex.getMessage());
		return new ResponseEntity<ExObject>(exObj,HttpStatus.NOT_FOUND);
		
	} 
	
	
	@ExceptionHandler(value = {DuplicateDataException.class})
	public ResponseEntity<ExObject> handleUDDEx(DuplicateDataException ex){
		ExObject exObj = new ExObject();
		exObj.setStatusCode(HttpStatus.CONFLICT.value());
		exObj.setMessage(ex.getMessage());
		return new ResponseEntity<ExObject>(exObj,HttpStatus.CONFLICT);
		
	} 
}
