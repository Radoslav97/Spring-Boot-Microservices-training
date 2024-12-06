package com.example.demo.exception;

public class DoctorAlreadyExistsException extends RuntimeException {
	
	private String message;
	
	public DoctorAlreadyExistsException() {
		this("already exists!!");
	}

	public DoctorAlreadyExistsException(String string) {
		// TODO Auto-generated constructor stub
	}

}
