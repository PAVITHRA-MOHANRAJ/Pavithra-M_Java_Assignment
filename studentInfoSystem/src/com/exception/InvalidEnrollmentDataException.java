package com.exception;

public class InvalidEnrollmentDataException extends Exception{

	private static final long serialVersionUID = 1L;
	String message;
	public InvalidEnrollmentDataException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	

}
