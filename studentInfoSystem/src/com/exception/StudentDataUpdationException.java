package com.exception;

public class StudentDataUpdationException extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;
	public StudentDataUpdationException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	
	

}
