package com.demo.authsrv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.authsrv.domain.AuthToken;
import com.demo.authsrv.domain.UserInfo;
import com.demo.authsrv.exception.UnAuthorizedException;
import com.demo.authsrv.service.AuthenticateService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class AuthenticateController {

	@Autowired
	private AuthenticateService authService;

	@PostMapping("/login")
	public ResponseEntity<AuthToken> login(@RequestBody UserInfo user)
			throws JsonProcessingException, UnAuthorizedException {
		if (user.getUsername() == null || user.getPassword() == null)
			throw new UnAuthorizedException("UserName And Password should not be empty!",HttpStatus.BAD_REQUEST);
		return new ResponseEntity<AuthToken>(authService.login(user), HttpStatus.OK);
	}

}
