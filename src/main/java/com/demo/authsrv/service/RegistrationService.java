package com.demo.authsrv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.authsrv.domain.UserInfo;
import com.demo.authsrv.exception.UserException;
import com.demo.authsrv.repo.RegistrationRepo;

@Service
public class RegistrationService {

	@Autowired
	private RegistrationRepo registrationRepo;

	public UserInfo userReg(UserInfo user) throws UserException {
		if(getUserByUserName(user.getUsername()) != null)
			throw new UserException("Username already exist");
		return registrationRepo.save(user);
	}
	
	public List<UserInfo> getAllUser() {
		return (List<UserInfo>) registrationRepo.findAll();
	}

	public UserInfo getUserByUserName(String username) {
		return registrationRepo.findByusername(username);
	}
}
