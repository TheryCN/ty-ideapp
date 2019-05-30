package com.github.therycn.tyideapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.therycn.tyideapp.exception.ValidationException;

/**
 * Rest Exception Handler.
 * 
 * @author TCHARASS
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler({ ValidationException.class })
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		ValidationException validationEx = (ValidationException) ex;

		List<String> messages = new ArrayList<>();
		validationEx.getErrorCodes().forEach(
				errorCode -> messages.add(messageSource.getMessage(errorCode, new Object[] {}, request.getLocale())));

		return new ResponseEntity<Object>(messages, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
