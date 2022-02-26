package com.safetynet.alerts.exceptions;
/**
 * AlreadyExistsException an exception that throws when an object already exists
 * in the data
 * 
 * @author Fatima
 *
 */
public class AlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * a class constructor
	 * 
	 * @param message
	 * 
	 */
	public AlreadyExistsException(String message) {
		super(message);
	}

}
