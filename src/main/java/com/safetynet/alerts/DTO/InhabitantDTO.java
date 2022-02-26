package com.safetynet.alerts.DTO;

import java.util.List;
/**
 * InhabitantDTO data transfer object :used to contain the inhabitant's
 * information with his medical file
 * 
 * @author Fatima
 *
 */
public class InhabitantDTO {
	/**
	 * inhabitant's fire name
	 */
	private String firstName;
	/**
	 * inhabitant's last name
	 */
	private String lastName;
	/**
	 * inhabitant's age
	 */
	private int age;
	/**
	 * inhabitant's phone number
	 */
	private String phone;
	/**
	 * inhabitant's Medication
	 */
	private List<String> medications;
	/**
	 * inhabitant's allergies
	 */
	private List<String> allergies;
	/**
	 * empty class constructor
	 */
	public InhabitantDTO() {
	}
	/**
	 * all fields class constructor
	 * 
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param phone
	 * @param medications
	 * @param allergies
	 */
	public InhabitantDTO(String firstName, String lastName, int age,
			String phone, List<String> medications, List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.phone = phone;
		this.medications = medications;
		this.allergies = allergies;
	}

	/**
	 * Getter of the inhabitant's firstName.
	 * 
	 * @return inhabitant's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter of inhabitant's firstName
	 * 
	 * @param firstName
	 *            to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter of inhabitant's last name
	 * 
	 * @return inhabitant's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter of inhabitant's last name
	 * 
	 * @param lastName
	 *            to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter of inhabitant's age
	 * 
	 * @return inhabitant's age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Setter of inhabitant's age
	 * 
	 * @param age
	 *            to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * Getter of inhabitant's phone number
	 * 
	 * @return inhabitant's phone number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Setter of inhabitant's phone number
	 * 
	 * @param phone
	 *            to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Getter of inhabitant's Medications
	 * 
	 * @return inhabitant's medications
	 */
	public List<String> getMedications() {
		return medications;
	}

	/**
	 * Setter of inhabitant's Medications
	 * 
	 * @param medications
	 *            to set
	 */
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	/**
	 * Getter of inhabitant's allergies
	 * 
	 * @return inhabitant's allergies
	 */
	public List<String> getAllergies() {
		return allergies;
	}

	/**
	 * Setter of inhabitant's allergies
	 * 
	 * @param allergies
	 *            to set
	 */
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

}
