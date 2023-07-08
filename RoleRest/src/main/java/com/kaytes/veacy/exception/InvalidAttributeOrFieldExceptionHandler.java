/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kaytes.veacy.dto.ApiReturnResponse;
import com.kaytes.veacy.properties.MessageProperties;
import com.kaytes.veacy.support.Util;

@ControllerAdvice
public class InvalidAttributeOrFieldExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	Util util;
	
	@Autowired
	MessageProperties messageProperties;
	
	@ExceptionHandler(InvalidAttributeOrFieldException.class)
	public ResponseEntity<ApiReturnResponse> handleInvalidAttributeOrFieldException(InvalidAttributeOrFieldException ex, WebRequest request){
		ApiReturnResponse apiReturnResponse = util.setApiReturnResponseMessage(ex.getMessage(),
				Boolean.FALSE, messageProperties.getErrorCode400());
		return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NullPointerException.class)
	 public ResponseEntity<ApiReturnResponse> handleNullPointerExceptions(Exception ex, WebRequest request) {
		ApiReturnResponse apiReturnResponse = util.setApiReturnResponseMessage(ex.getMessage(),
				Boolean.FALSE, messageProperties.getErrorCode400());
		return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	 public ResponseEntity<ApiReturnResponse> handleIllegalArgumentExceptions(Exception ex, WebRequest request) {
		ApiReturnResponse apiReturnResponse = util.setApiReturnResponseMessage(ex.getMessage(),
				Boolean.FALSE, messageProperties.getErrorCode400());
		return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	 public ResponseEntity<ApiReturnResponse> handleAllOtherExceptions(Exception ex, WebRequest request) {
		ApiReturnResponse apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getInternalServerError(),
				Boolean.FALSE, messageProperties.getErrorCode500());
		return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
}
