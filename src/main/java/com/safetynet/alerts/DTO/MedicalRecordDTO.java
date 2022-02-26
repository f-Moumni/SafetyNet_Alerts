package com.safetynet.alerts.DTO;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;
/**
 * MedicalRecordDTO data transfer object :used to contain a person's medical
 * record
 * 
 * @see MedicalRecord
 * @author Fatima
 *
 */
public class MedicalRecordDTO {
	/**
	 * person's first name
	 */
	private String firstName;

	/**
	 * person's last name
	 */
	private String lastName;

	/*
	 * person's birthdate
	 */
	private String birthdate;

	/**
	 * person's medications
	 */
	private List<String> medications;

	/**
	 * person's allergies
	 */
	private List<String> allergies;

	/**
	 * empty class constructor
	 */
	public MedicalRecordDTO() {
	}
	/**
	 * all fields class constructor
	 * 
	 * @param firstName
	 * @param lastName
	 * @param birthdate
	 * @param medications
	 * @param allergies
	 */
	public MedicalRecordDTO(String firstName, String lastName, String birthdate,
			List<String> medications, List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

	/**
	 * Getter of person's first name
	 * 
	 * @return person's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter of person's first name
	 * 
	 * @param firstName
	 *            to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter of person's last name
	 * 
	 * @return person's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter of person's last name
	 * 
	 * @param lastName
	 *            to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter of person's birth date
	 * 
	 * @return person's birth date
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * Setter of person's birth date
	 * 
	 * @param birthdate
	 *            to set
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * Getter of person's medications
	 * 
	 * @return person's medications
	 */
	public List<String> getMedications() {
		return medications;
	}

	/**
	 * Setter of person's medications
	 * 
	 * @param medications
	 *            to set
	 */
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	/**
	 * Getter of person's allergies
	 * 
	 * @return person's allergies
	 */
	public List<String> getAllergies() {
		return allergies;
	}

	/**
	 * Setter of person's allergies
	 * 
	 * @param allergies
	 *            to set
	 */
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	/**
	 * Serialization toString method.
	 */
	@Override
	public String toString() {
		return "MedicalRecordDTO [firstName=" + firstName + ", lastName="
				+ lastName + ", birthdate=" + birthdate + ", medications="
				+ medications + ", allergies=" + allergies + "]";
	}

}
