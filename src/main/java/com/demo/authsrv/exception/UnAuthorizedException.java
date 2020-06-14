package com.demo.authsrv.exception;

import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends RuntimeException {

	 private final HttpStatus status;
	 
	public UnAuthorizedException(String message,Throwable t, HttpStatus status) {
		super(message, t);
		this.status = status;
	}
	
	public UnAuthorizedException(String message,HttpStatus status) {
		 super(message);
	     this.status = status;
	}
	
	public HttpStatus getStatus() {
        return this.status;
    }
}
