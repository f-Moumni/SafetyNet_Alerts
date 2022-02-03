package com.safetynet.alerts.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<?> handleDataNoteFoundException(
			DataNotFoundException e, WebRequest request) {

		ExceptionDetails exception = new ExceptionDetails(new Date(),
				e.getMessage(), HttpStatus.NOT_FOUND,
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleBadRequestException(BadRequestException e,
			WebRequest request) {

		ExceptionDetails exception = new ExceptionDetails(new Date(),
				e.getMessage(), HttpStatus.BAD_REQUEST,
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<?> handlePersonNotFoundException(
			PersonNotFoundException e, WebRequest request) {

		ExceptionDetails exception = new ExceptionDetails(new Date(),
				e.getMessage(), HttpStatus.NOT_FOUND,
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<?> handleAlreadyExistsException(
			AlreadyExistsException e, WebRequest request) {

		ExceptionDetails exception = new ExceptionDetails(new Date(),
				e.getMessage(), HttpStatus.ALREADY_REPORTED,
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.ALREADY_REPORTED);

	}

}
