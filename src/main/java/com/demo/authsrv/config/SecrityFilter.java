package com.demo.authsrv.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.authsrv.exception.Meta;
import com.demo.authsrv.exception.UnAuthorizedException;
import com.demo.authsrv.service.AuthenticateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SecrityFilter implements Filter {

	@Autowired
	private AuthenticateService authService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
			authService.authenticate(httpServletRequest);
			chain.doFilter(request, response);
		} catch (UnAuthorizedException e) {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.setStatus(e.getStatus().value());
			resp.setContentType("application/json");
			resp.getWriter().write(convertObjectToJson(new Meta(e.getMessage(), e.getStatus().value())));
		}
	}

	public String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
