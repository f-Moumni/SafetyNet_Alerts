package com.safetynet.alerts.DTO;
/**
 * PersonDTO data transfer object :used to contain person's informations
 * 
 * @author Fatima
 *
 */
public class PersonDTO {
	/**
	 * person's first name
	 */
	private String firstName;

	/**
	 * person's last name
	 */
	private String lastName;

	/**
	 * person's address
	 */
	private String address;

	/**
	 * the person's city
	 */
	private String city;

	/**
	 * the person's zip
	 */
	private int zip;

	/**
	 * the person's phone
	 */
	private String phone;

	/**
	 * the person's email
	 */
	private String email;

	/**
	 * empty class constructor
	 */
	public PersonDTO() {
	}

	/**
	 * all fields class constructor
	 * 
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param city
	 * @param zip
	 * @param phone
	 * @param email
	 */
	public PersonDTO(String firstName, String lastName, String address,
			String city, int zip, String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
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
	 * Getter of person's address
	 * 
	 * @return person's address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter of person's address
	 * 
	 * @param address
	 *            to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Getter of person's city
	 * 
	 * @return person's city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * Setter of person's city
	 * 
	 * @param city
	 *            to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Getter of person's zip
	 * 
	 * @return person's zip
	 */
	public int getZip() {
		return zip;
	}

	/**
	 * Setter of person's zip
	 * 
	 * @param zip
	 *            to set
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}

	/**
	 * Getter of person's phone
	 * 
	 * @return person's phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Setter of person's phone
	 * 
	 * @param phone
	 *            to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Getter of person's email
	 * 
	 * @return person's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter of person's email
	 * 
	 * @param email
	 *            to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Serialization toString method.
	 */
	@Override
	public String toString() {
		return "PersonDTO [firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", city=" + city + ", zip=" + zip
				+ ", phone=" + phone + "]";
	}

}
