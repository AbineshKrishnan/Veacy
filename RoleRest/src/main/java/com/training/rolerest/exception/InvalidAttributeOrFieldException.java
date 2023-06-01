package com.training.rolerest.exception;

public class InvalidAttributeOrFieldException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidAttributeOrFieldException(String message) {
		super(message);
	}
}
