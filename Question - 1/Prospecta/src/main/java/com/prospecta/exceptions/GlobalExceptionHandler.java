package com.prospecta.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidEntryException.class)
	public ResponseEntity<ErrorDetails> customerExceptionHandler(InvalidEntryException se, WebRequest req) {

		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(se.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ErrorDetails>(err, HttpStatus.NOT_FOUND);

	}

}
