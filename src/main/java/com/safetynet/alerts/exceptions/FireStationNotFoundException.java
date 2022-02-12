package com.safetynet.alerts.exceptions;

public class FireStationNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FireStationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public FireStationNotFoundException(String message) {
		super(message);
	}

}
