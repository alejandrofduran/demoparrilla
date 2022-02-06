package com.demo.parrilla.handler;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.parrilla.exceptions.GrillFilesException;
import com.demo.parrilla.exceptions.GrillReadFilesException;
import com.demo.parrilla.service.ErrorMessageService;
import com.demo.parrilla.utils.ApiError;
import com.demo.parrilla.utils.StandardRestResponse;

@RestControllerAdvice
public class GrillExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GrillExceptionHandler.class);
	
	@Autowired
	ErrorMessageService errorMessageService;
		
	@ExceptionHandler({ GrillFilesException.class })
	public ResponseEntity<Object> handle(GrillFilesException e, HttpServletRequest request) {	
		logger.error("GrillFilesException", e.getMessage());
		return buildResponse(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ GrillReadFilesException.class })
	public ResponseEntity<Object> handle(GrillReadFilesException e, HttpServletRequest request) {	
		logger.error("GrillReadFilesException", e.getMessage());
		return buildResponse(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handle(Exception e, HttpServletRequest request) {
		logger.error("Exception", e.getMessage());
		System.out.println(e);
		return buildResponse(errorMessageService.getMessage("exceptionErrorCode"), errorMessageService.getMessage("genericErrorMessage"), 
				HttpStatus.BAD_REQUEST, request);
	}
	
	private ResponseEntity<Object> buildResponse(String errorCode, String errorMessage, HttpStatus status,
			HttpServletRequest request) {
		String finalErrorCode = (StringUtils.isNotEmpty(errorCode)) ? errorCode : errorMessageService.getMessage("genericErrorCode");
		String finalErrorMessage = (StringUtils.isNotEmpty(errorMessage)) ? errorMessage : errorMessageService.getMessage("genericErrorMessage");

		StandardRestResponse<Object, Object> standardRestresponse = StandardRestResponse.method(request.getMethod())
				.operation(request.getRequestURI())
				.errors(Arrays.asList(new ApiError(finalErrorCode, finalErrorMessage)));

		return new ResponseEntity<>(standardRestresponse, status);
	}
	
}
