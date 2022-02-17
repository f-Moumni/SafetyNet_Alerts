package com.safetynet.alerts.DTO;

import java.util.ArrayList;
import java.util.List;

public class ChildDTO {
	private String firstName;
	private String lastName;
	private int age;
	private List<String> family = new ArrayList<>();
	public ChildDTO() {

	}
	public ChildDTO(String firstName, String lastName, int age,
			List<String> family) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.family = family;
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
	public List<String> getFamily() {
		return family;
	}
	public void setFamily(List<String> family) {
		this.family = family;
	}
	@Override
	public String toString() {
		return "ChildDTO [firstName=" + firstName + ", lastName=" + lastName
				+ ", age=" + age + ", family=" + family + "]";
	}

}