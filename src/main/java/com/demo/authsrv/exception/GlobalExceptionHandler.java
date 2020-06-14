package com.demo.authsrv.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler(value = UnAuthorizedException.class)
    protected ResponseEntity<Object> handelUnAuthExp(
      UnAuthorizedException ex, WebRequest request) {
        return handleExceptionInternal(ex, new Meta(ex.getMessage(),ex.getStatus().value()) , 
          new HttpHeaders(), ex.getStatus(), request);
    }
    
    @ExceptionHandler(value = UserException.class)
    protected ResponseEntity<Object> handelUserExp(
    		UserException ex, WebRequest request) {
        return handleExceptionInternal(ex, new Meta(ex.getMessage(),HttpStatus.FORBIDDEN.value()) , 
          new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
}