package com.exception;

public class DuplicateEnrollmentException extends Exception{

	private static final long serialVersionUID = 1L;
	String message;
	public DuplicateEnrollmentException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	
}
