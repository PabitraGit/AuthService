package com.demo.authsrv.repo;

import org.springframework.data.repository.CrudRepository;

import com.demo.authsrv.domain.UserInfo;

public interface RegistrationRepo extends CrudRepository<UserInfo, Integer>{
	
	UserInfo findByusername(String username);
}
