package com.safetynet.alerts.DTO;

import java.util.List;

public class PersonInfosDTO {
	private String firstName;
	private String lastName;
	private int age;
	private String mail;
	private List<String> medications;
	private List<String> allergies;

	public PersonInfosDTO() {
		super();
	}

	public PersonInfosDTO(String firstName, String lastName, int age,
			String mail, List<String> medications, List<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.mail = mail;
		this.medications = medications;
		this.allergies = allergies;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	@Override
	public String toString() {
		return "PersonInfosDTO [firstName=" + firstName + ", lastName="
				+ lastName + ", age=" + age + ", mail=" + mail
				+ ", medications=" + medications + ", allergies=" + allergies
				+ "]";
	}

}
