package com.safetynet.alerts.exceptions;
/**
 * FireStationNotFoundException an exception that throws when an firestation not
 * found
 * 
 * @author Fatima
 *
 */
public class FireStationNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * a class constructor
	 * 
	 * @param message
	 */
	public FireStationNotFoundException(String message) {
		super(message);
	}

}
