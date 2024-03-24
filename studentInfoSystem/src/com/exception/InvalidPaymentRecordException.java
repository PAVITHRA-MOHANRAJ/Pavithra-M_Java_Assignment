package com.exception;

public class InvalidPaymentRecordException extends Exception{

	private static final long serialVersionUID = 1L;
	String message;
	
	public InvalidPaymentRecordException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	

}
