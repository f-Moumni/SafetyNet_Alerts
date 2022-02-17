package com.safetynet.alerts.exceptions;

import java.time.LocalDateTime;

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

		ExceptionDetails exception = new ExceptionDetails(LocalDateTime.now(),
				e.getMessage(), HttpStatus.NOT_FOUND,
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<?> handlePersonNotFoundException(
			PersonNotFoundException e, WebRequest request) {

		ExceptionDetails exception = new ExceptionDetails(LocalDateTime.now(),
				e.getMessage(), HttpStatus.NOT_FOUND,
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<?> handleAlreadyExistsException(
			AlreadyExistsException e, WebRequest request) {

		ExceptionDetails exception = new ExceptionDetails(LocalDateTime.now(),
				e.getMessage(), HttpStatus.ALREADY_REPORTED,
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.ALREADY_REPORTED);

	}
	@ExceptionHandler(FireStationNotFoundException.class)
	public ResponseEntity<?> handleFireStationNotFoundException(
			FireStationNotFoundException e, WebRequest request) {

		ExceptionDetails exception = new ExceptionDetails(LocalDateTime.now(),
				e.getMessage(), HttpStatus.NOT_FOUND,
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(MedicalRecordNotFoundException.class)
	public ResponseEntity<?> handleMedicalRecordNotFoundException(
			MedicalRecordNotFoundException e, WebRequest request) {

		ExceptionDetails exception = new ExceptionDetails(LocalDateTime.now(),
				e.getMessage(), HttpStatus.NOT_FOUND,
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);

	}
}
