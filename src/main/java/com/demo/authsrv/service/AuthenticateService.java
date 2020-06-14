package com.demo.authsrv.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demo.authsrv.config.EndpointsListener;
import com.demo.authsrv.domain.AuthToken;
import com.demo.authsrv.domain.UserInfo;
import com.demo.authsrv.exception.UnAuthorizedException;
import com.demo.authsrv.util.JwtTokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class AuthenticateService {

	@Autowired
	private JwtTokenUtil jwtUtil;

	@Autowired
	private RegistrationService regServ;

	@Autowired
	EndpointsListener endpoints;

	private final String REG_URI = "/registration/user";
	private final String AUTH_URI = "/login";

	public AuthToken login(UserInfo userInfo) throws JsonProcessingException {
		// check user existence
		UserInfo user = regServ.getUserByUserName(userInfo.getUsername());
		if (user == null)
			throw new UnAuthorizedException("UnAuthorized Request!User Not Found!", HttpStatus.UNAUTHORIZED);
		if (!user.getPassword().equals(userInfo.getPassword())) {
			throw new UnAuthorizedException("UnAuthorized Request!Username and Password does not match!", HttpStatus.UNAUTHORIZED);
		}
		return jwtUtil.getAuthToken(user);
	}

	public void authenticate(HttpServletRequest httpServletRequest) {
		String requestURI = httpServletRequest.getRequestURI();
		if (!requestURI.equals(AUTH_URI) && !requestURI.equals(REG_URI)) {
			List<String> URIs = endpoints.getAllURIs();
			if (URIs.contains(requestURI)) {
				String token = httpServletRequest.getHeader("Authorization");
				if (StringUtils.isBlank(token))
					throw new UnAuthorizedException("Authorization header required", HttpStatus.UNAUTHORIZED);
				try {
					String username = jwtUtil.getUsernameFromToken(token);
					if (StringUtils.isBlank(username))
						throw new UnAuthorizedException("UnAuthorization request header", HttpStatus.UNAUTHORIZED);
					UserInfo user = regServ.getUserByUserName(username);
					if (user == null)
						throw new UnAuthorizedException("UnAuthorized Request!User Not Found!",
								HttpStatus.UNAUTHORIZED);
					if (!jwtUtil.validateToken(token, user))
						throw new UnAuthorizedException("UnAuthorization request header", HttpStatus.UNAUTHORIZED);
				} catch (Exception e) {
					throw new UnAuthorizedException("UnAuthorization request header", HttpStatus.UNAUTHORIZED);
				}
			}
		}
	}

}
