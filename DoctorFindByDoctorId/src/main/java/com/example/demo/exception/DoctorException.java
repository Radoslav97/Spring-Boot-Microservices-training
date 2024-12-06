package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DoctorException extends RuntimeException {
	
	public DoctorException(String message) {
		super(message);
	}
	
	public DoctorException(String message, Throwable cause) {
		super(message, cause);
	}
}
