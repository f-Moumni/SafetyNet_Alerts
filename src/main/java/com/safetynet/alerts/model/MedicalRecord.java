package com.safetynet.alerts.model;

import java.util.List;
import java.util.Objects;

public class MedicalRecord {

	private String firstName;
	private String lastName;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;

	public MedicalRecord() {
		super();
	}
	public MedicalRecord(String firstName, String lastName, String birthdate,
			List<String> medications, List<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
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
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
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
		return "MedicalRecord [firstName=" + firstName + ", lastName="
				+ lastName + ", birthdate=" + birthdate + ", medications="
				+ medications + ", allergies=" + allergies + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicalRecord other = (MedicalRecord) obj;
		return Objects.equals(allergies, other.allergies)
				&& Objects.equals(birthdate, other.birthdate)
				&& Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName)
				&& Objects.equals(medications, other.medications);
	}

}
