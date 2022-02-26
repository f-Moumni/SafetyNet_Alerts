package com.safetynet.alerts.exceptions;
/**
 * MedicalRecordNotFoundException an exception that throws when an MedicalRecord
 * not found
 * 
 * @author Fatima
 *
 */
public class MedicalRecordNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * a class constructor
	 * 
	 * @param message
	 */
	public MedicalRecordNotFoundException(String message) {
		super(message);
	}

}
