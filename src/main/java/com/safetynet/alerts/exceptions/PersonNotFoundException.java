package com.safetynet.alerts.exceptions;

/**
 * PersonNotFoundException an exception that throws when an Person not found
 * 
 * @author Fatima
 *
 */

public class PersonNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * a class constructor
	 * 
	 * @param message
	 * 
	 */
	public PersonNotFoundException(String message) {
		super(message);
	}
}
