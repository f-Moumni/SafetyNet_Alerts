package com.safetynet.alerts.DTO;

import java.util.ArrayList;
import java.util.List;
/**
 * ChildDTO data transfer object :used to contain person under 18 years old
 * 
 * @author Fatima
 *
 */
public class ChildDTO {
	/**
	 * firstName child first name
	 */
	private String firstName;

	/**
	 * lastName child last name
	 */
	private String lastName;

	/**
	 * child age
	 */
	private int age;

	/**
	 * family a list of names of people who live in the same household
	 */
	private List<String> family = new ArrayList<>();

	/*
	 * ChildDTO empty constructor
	 */
	public ChildDTO() {
	}

	/**
	 * ChildDTO all fields constructor
	 * 
	 * @param firstName
	 *            child first name
	 * @param lastName
	 *            child last name
	 * @param age
	 *            child age
	 * @param family
	 *            a list of names of people who live in the same home
	 */
	public ChildDTO(String firstName, String lastName, int age,
			List<String> family) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.family = family;
	}

	/**
	 * Getter of the child firstName.
	 * 
	 * @return child first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter of the child firstName
	 * 
	 * @param firstName
	 *            child first name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter of the child lastName.
	 * 
	 * @return child lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter of the child lastName
	 * 
	 * @param lastName
	 *            to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter of the child age
	 * 
	 * @return age of child
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Setter of the child age
	 * 
	 * @param age
	 *            to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Getter of child family
	 * 
	 * @return a list of names of people who live in the same home
	 */
	public List<String> getFamily() {
		return family;
	}

	/**
	 * Setter of child family
	 * 
	 * @param family
	 *            a list of names of people who live in the same home
	 */
	public void setFamily(List<String> family) {
		this.family = family;
	}

	/**
	 * Serialization toString method.
	 */
	@Override
	public String toString() {
		return "ChildDTO [firstName=" + firstName + ", lastName=" + lastName
				+ ", age=" + age + ", family=" + family + "]";
	}

}