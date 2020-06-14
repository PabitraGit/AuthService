package com.demo.authsrv.domain;

import java.io.Serializable;
import java.util.Date;

public class AuthToken implements Serializable{
	private static final long serialVersionUID = 4859238203079684271L;
	
	private String access_token;
	private Date expiry;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Date getExpiry() {
		return expiry;
	}
	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
	public AuthToken(String access_token, Date expiry) {
		this.access_token = access_token;
		this.expiry = expiry;
	}
	public AuthToken() {}
}
