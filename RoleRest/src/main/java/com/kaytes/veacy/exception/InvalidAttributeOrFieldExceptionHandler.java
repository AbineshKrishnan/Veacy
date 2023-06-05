/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InvalidAttributeOrFieldExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InvalidAttributeOrFieldException.class)
	public ResponseEntity<Object> handleInvalidAttributeOrFieldException(InvalidAttributeOrFieldException ex, WebRequest request){
		System.out.println("\n\nHandler Handled.....");
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NullPointerException.class)
	 public ResponseEntity<Object> handleNullPointerExceptions(Exception ex, WebRequest request) {
	 ErrorResponse errorResponse = new ErrorResponse();
	 errorResponse.setMessage("Invalid Attribute Exception");
	 errorResponse.setTimeStamp(LocalDateTime.now());
	 return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	
	@ExceptionHandler(Exception.class)
	 public ResponseEntity<Object> handleAllOtherExceptions(Exception ex, WebRequest request) {
	 ErrorResponse errorResponse = new ErrorResponse();
	 errorResponse.setMessage(ex.getMessage());
	 errorResponse.setTimeStamp(LocalDateTime.now());
	 return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
}
