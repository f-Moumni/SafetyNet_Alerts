package com.safetynet.alerts.DTO;
/**
 * PersonAlertDTO data transfer object :used to contain person's informations
 * 
 * @author Fatima
 *
 */
public class PersonAlertDTO {
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
	 * person's last phone number
	 */
	private String phone;

	/**
	 * empty class constructor
	 */
	public PersonAlertDTO() {
	}

	/**
	 * all fields class constructor
	 * 
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param phone
	 */
	public PersonAlertDTO(String firstName, String lastName, String address,
			String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
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
	 * Getter of person's phone
	 * 
	 * @return person's phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Getter of person's phone
	 * 
	 * @param phone
	 *            to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * Serialization toString method.
	 */
	@Override
	public String toString() {
		return "PersonCouverdDTO [firstName=" + firstName + ", lastName="
				+ lastName + ", address=" + address + ", phone=" + phone + "]";
	}

}
