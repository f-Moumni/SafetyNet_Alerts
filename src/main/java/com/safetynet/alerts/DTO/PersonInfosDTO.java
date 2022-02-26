package com.safetynet.alerts.DTO;

import java.util.List;
/**
 * PersonInfosDTO data transfer object :used to contain person's informations
 * with medical record
 * 
 * @author Fatima
 *
 */
public class PersonInfosDTO {
	/**
	 * person's first name
	 */
	private String firstName;
	/**
	 * person's last name
	 */
	private String lastName;

	/**
	 * person's age
	 */
	private int age;

	/**
	 * person's eamil
	 */
	private String mail;

	/**
	 * person's Medication
	 */
	private List<String> medications;

	/**
	 * person's allergies
	 */
	private List<String> allergies;

	/**
	 * empty class constructor
	 */
	public PersonInfosDTO() {
	}

	/**
	 * all fields class constructor
	 * 
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param mail
	 * @param medications
	 * @param allergies
	 */
	public PersonInfosDTO(String firstName, String lastName, int age,
			String mail, List<String> medications, List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.mail = mail;
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
	 * Getter of person's age
	 * 
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Setter of person's age
	 * 
	 * @param age
	 *            to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Getter of person's email
	 * 
	 * @return person's email
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Setter of person's email
	 * 
	 * @param mail
	 *            to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Getter to person's Medications
	 * 
	 * @return person's Medications
	 */
	public List<String> getMedications() {
		return medications;
	}
	/**
	 * Setter to person's Medications
	 * 
	 * @param medications
	 *            to set
	 */
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	/**
	 * Getter to person's allergies
	 * 
	 * @return person's allergies
	 */
	public List<String> getAllergies() {
		return allergies;
	}

	/**
	 * Setter to person's allergies
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
		return "PersonInfosDTO [firstName=" + firstName + ", lastName="
				+ lastName + ", age=" + age + ", mail=" + mail
				+ ", medications=" + medications + ", allergies=" + allergies
				+ "]";
	}

}
