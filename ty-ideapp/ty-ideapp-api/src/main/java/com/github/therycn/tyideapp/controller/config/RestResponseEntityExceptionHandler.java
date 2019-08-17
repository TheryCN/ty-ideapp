package com.github.therycn.tyideapp.controller.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.therycn.tyideapp.exception.UsernameAlreadyExistsException;
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            try {
                String errorMessageTranslated = messageSource.getMessage(error.getDefaultMessage(), new Object[] {},
                        request.getLocale());
                errors.put(fieldName, errorMessageTranslated);
            } catch (NoSuchMessageException e) {
                errors.put(fieldName, error.getDefaultMessage());
            }
        });
        return new ResponseEntity<Object>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ UsernameAlreadyExistsException.class })
    public ResponseEntity<Object> handleConflictException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(getValidationExceptionMessage(ex, request), new HttpHeaders(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ ValidationException.class })
    public ResponseEntity<Object> handleValidationException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(getValidationExceptionMessage(ex, request), new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    private String getValidationExceptionMessage(Exception ex, WebRequest request) {
        ValidationException validationEx = (ValidationException) ex;
        return messageSource.getMessage(validationEx.getMessage(), new Object[] {}, request.getLocale());
    }
}
