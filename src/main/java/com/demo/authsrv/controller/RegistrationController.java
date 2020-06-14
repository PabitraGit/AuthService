package com.demo.authsrv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.authsrv.domain.UserInfo;
import com.demo.authsrv.exception.UserException;
import com.demo.authsrv.service.RegistrationService;

@RestController
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping("/registration/user")
	public ResponseEntity<UserInfo> userReg(@RequestBody UserInfo user) throws UserException{
		UserInfo respUser = registrationService.userReg(user);
		return new ResponseEntity<UserInfo>(respUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserInfo>> getAllUser(){
		List<UserInfo> users = registrationService.getAllUser();
		return new ResponseEntity<List<UserInfo>>(users, HttpStatus.OK);
	}
	
	
}
