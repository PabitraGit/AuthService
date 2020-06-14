package com.demo.authsrv.exception;

public class Meta {
	private String message;
	private int status;
	public Meta(String message, int status) {
		this.message = message;
		this.status = status;
	}
	public Meta() {}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
