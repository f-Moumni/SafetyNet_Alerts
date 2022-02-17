package com.safetynet.alerts.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ExceptionDetails {
	private final LocalDateTime timestamp;
	private final String message;
	private final HttpStatus httpStatus;
	private final String details;
	public ExceptionDetails(LocalDateTime times, String message,
			HttpStatus httpStatus, String details) {

		this.timestamp = times;
		this.message = message;
		this.httpStatus = httpStatus;
		this.details = details;
	}
	public LocalDateTime getTimestamp() {
		return this.timestamp;
	}
	public String getMessage() {
		return message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public String getDetails() {
		return details;
	}

}