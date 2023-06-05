/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.exception;

public class InvalidAttributeOrFieldException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidAttributeOrFieldException(String message) {
		
		super(message);
	}
}
