package com.safetynet.alerts.exceptions;
/**
 * DataNotFoundException an exception that throws when no data is found
 * 
 * @author Fatima
 *
 */
public class DataNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * a class constructor
	 * 
	 * @param message
	 * 
	 */
	public DataNotFoundException(String message) {
		super(message);
	}
}
