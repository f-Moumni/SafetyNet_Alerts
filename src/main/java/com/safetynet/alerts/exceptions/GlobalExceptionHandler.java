package com.safetynet.alerts.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
/**
 * GlobalExceptionHandler a class that allows to handle exceptions
 * 
 * @author Fatima
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * handleDataNoteFoundException the handle method of DataNotFoundException
	 * 
	 * @param e
	 *            DataNotFoundException
	 * @param request
	 *            WebRequest
	 * @return
	 */
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Object> handleDataNoteFoundException(
			DataNotFoundException e, WebRequest request) {

		ExceptionDetails exception = new ExceptionDetails(LocalDateTime.now(),
				e.getMessage(), HttpStatus.NOT_FOUND,
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);

	}

	/**
	 * handlePersonNotFoundException the handle method of
	 * PersonNotFoundException
	 * 
	 * @param e
	 *            PersonNotFoundException
	 * @param request
	 *            WebRequest
	 * @return
	 */
	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<Object> handlePersonNotFoundException(
			PersonNotFoundException e, WebRequest request) {

		ExceptionDetails exception = new ExceptionDetails(LocalDateTime.now(),
				e.getMessage(), HttpStatus.NOT_FOUND,
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);

	}

	/**
	 * handleAlreadyExistsException the handle method of AlreadyExistsException
	 * 
	 * @param e
	 *            AlreadyExistsException
	 * @param request
	 *            WebRequest
	 * @return
	 */
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<Object> handleAlreadyExistsException(
			AlreadyExistsException e, WebRequest request) {

		ExceptionDetails exception = new ExceptionDetails(LocalDateTime.now(),
				e.getMessage(), HttpStatus.ALREADY_REPORTED,
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.ALREADY_REPORTED);

	}

	/**
	 * handleFireStationNotFoundException the handle method of
	 * FireStationNotFoundException
	 * 
	 * @param e
	 *            FireStationNotFoundException
	 * @param request
	 *            WebRequest
	 * @return
	 */
	@ExceptionHandler(FireStationNotFoundException.class)
	public ResponseEntity<Object> handleFireStationNotFoundException(
			FireStationNotFoundException e, WebRequest request) {

		ExceptionDetails exception = new ExceptionDetails(LocalDateTime.now(),
				e.getMessage(), HttpStatus.NOT_FOUND,
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);

	}

	/**
	 * handleMedicalRecordNotFoundException the handle method of
	 * MedicalRecordNotFoundException
	 * 
	 * @param e
	 *            MedicalRecordNotFoundException
	 * @param request
	 *            WebRequest
	 * @return
	 */
	@ExceptionHandler(MedicalRecordNotFoundException.class)
	public ResponseEntity<Object> handleMedicalRecordNotFoundException(
			MedicalRecordNotFoundException e, WebRequest request) {

		ExceptionDetails exception = new ExceptionDetails(LocalDateTime.now(),
				e.getMessage(), HttpStatus.NOT_FOUND,
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);

	}
}
