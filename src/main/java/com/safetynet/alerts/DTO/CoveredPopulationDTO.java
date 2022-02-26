package com.safetynet.alerts.DTO;

import java.util.ArrayList;
import java.util.List;
/**
 * Class of the model used to contain the count of children and adults (more
 * than 18 years old)
 * 
 * @author Fatima
 *
 */
public class CoveredPopulationDTO {
	/**
	 * The list of persons covered by the station.
	 */
	private List<PersonAlertDTO> PersonsCouverd = new ArrayList<PersonAlertDTO>();

	/**
	 * The count of adult (>18 years old).
	 */
	private int numberOfAdults;
	/**
	 * The count of children (18 and less).
	 */
	private int numberOfChildren;
	/**
	 * Empty class constructor.
	 */
	public CoveredPopulationDTO() {
	}

	/**
	 * all fields class constructor
	 * 
	 * @param personsCouverd
	 * @param numberOfAdults
	 * @param numberOfChildren
	 */
	public CoveredPopulationDTO(List<PersonAlertDTO> personsCouverd,
			int numberOfAdults, int numberOfChildren) {
		PersonsCouverd = personsCouverd;
		this.numberOfAdults = numberOfAdults;
		this.numberOfChildren = numberOfChildren;
	}
	/**
	 * Getter of PersonsCouverd;
	 * 
	 * @return the PersonsCouverd
	 */
	public List<PersonAlertDTO> getPersonsCouverd() {
		return PersonsCouverd;
	}
	/**
	 * Setter of PersonsCouverd
	 * 
	 * @param personsCouverd
	 *            to set
	 */
	public void setPersonsCouverd(List<PersonAlertDTO> personsCouverd) {
		PersonsCouverd = personsCouverd;
	}
	/**
	 * Getter of numberOfAdults
	 * 
	 * @return numberOfAdults
	 */
	public int getNumberOfAdults() {
		return numberOfAdults;
	}
	/**
	 * Setter of numberOfAdults
	 * 
	 * @param numberOfAdults
	 *            to set
	 */
	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}
	/**
	 * Getter of numberOfChildren
	 * 
	 * @return numberOfChildren
	 */
	public int getNumberOfChildren() {
		return numberOfChildren;
	}
	/**
	 * Setter to numberOfChildren
	 * 
	 * @param numberOfChildren
	 *            to set
	 */
	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}
	/**
	 * Serialization toString method.
	 */
	@Override
	public String toString() {
		return "CoveredPopulationDTO [PersonsCouverd=" + PersonsCouverd
				+ ", numberOfAdults=" + numberOfAdults + ", numberOfChildren="
				+ numberOfChildren + "]";
	}

}
