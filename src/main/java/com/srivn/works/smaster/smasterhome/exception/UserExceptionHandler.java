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
	
	@ExceptionHandler(value = {NoAccessException.class})
	public ResponseEntity<ExObject> handleNAEx(NoAccessException ex){
		ExObject exObj = new ExObject();
		exObj.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		exObj.setMessage(ex.getMessage());
		return new ResponseEntity<ExObject>(exObj,HttpStatus.UNAUTHORIZED);
		
	} 
	
	@ExceptionHandler(value = {BadRequestException.class})
	public ResponseEntity<ExObject> handleBRDEx(BadRequestException ex){
		ExObject exObj = new ExObject();
		exObj.setStatusCode(HttpStatus.BAD_REQUEST.value());
		exObj.setMessage(ex.getMessage());
		return new ResponseEntity<ExObject>(exObj,HttpStatus.BAD_REQUEST);
		
	} 
}
