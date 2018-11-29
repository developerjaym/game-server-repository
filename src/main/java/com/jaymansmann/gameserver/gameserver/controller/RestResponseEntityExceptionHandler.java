package com.jaymansmann.gameserver.gameserver.controller;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ValidationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger LOGGER = Logger.getLogger(RestResponseEntityExceptionHandler.class.getName());
    
	@ExceptionHandler(value = { IllegalArgumentException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Your request had an illegal argument.";
		LOGGER.log(Level.SEVERE, ex.toString(), ex);
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(value = { NoSuchElementException.class })
	protected ResponseEntity<Object> handleNoSucElementException(RuntimeException ex, WebRequest request) {
		LOGGER.log(Level.SEVERE, ex.toString(), ex);
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}


	@ExceptionHandler(value = { RuntimeException.class })
	protected ResponseEntity<Object> handleOtherException(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Unknown server error.";
		LOGGER.log(Level.SEVERE, ex.toString(), ex);
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { ValidationException.class })
	protected ResponseEntity<Object> handleValidationException(RuntimeException ex, WebRequest request) {
		LOGGER.log(Level.SEVERE, ex.toString(), ex);
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

}