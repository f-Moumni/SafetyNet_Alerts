package com.safetynet.alerts.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ExceptionDetails {
	private final Date timestamp;
	private final String message;
	private final HttpStatus httpStatus;
	private final String details;
	public ExceptionDetails(Date timestamp, String message,
			HttpStatus httpStatus, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.httpStatus = httpStatus;
		this.details = details;
	}
	public Date getTimestamp() {
		return timestamp;
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