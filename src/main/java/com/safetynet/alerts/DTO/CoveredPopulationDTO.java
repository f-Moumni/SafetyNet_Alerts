package com.safetynet.alerts.DTO;

import java.util.ArrayList;
import java.util.List;

public class CoveredPopulationDTO {

	private List<PersonAlertDTO> PersonsCouverd = new ArrayList<PersonAlertDTO>();
	private int numberOfAdults;
	private int numberOfChildren;
	public CoveredPopulationDTO() {
		super();
	}
	public CoveredPopulationDTO(List<PersonAlertDTO> personsCouverd,
			int numberOfAdults, int numberOfChildren) {

		PersonsCouverd = personsCouverd;
		this.numberOfAdults = numberOfAdults;
		this.numberOfChildren = numberOfChildren;
	}

	public List<PersonAlertDTO> getPersonsCouverd() {
		return PersonsCouverd;
	}
	public void setPersonsCouverd(List<PersonAlertDTO> personsCouverd) {
		PersonsCouverd = personsCouverd;
	}
	public int getNumberOfAdults() {
		return numberOfAdults;
	}
	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}
	public int getNumberOfChildren() {
		return numberOfChildren;
	}
	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	@Override
	public String toString() {
		return "CoveredPopulationDTO [PersonsCouverd=" + PersonsCouverd
				+ ", numberOfAdults=" + numberOfAdults + ", numberOfChildren="
				+ numberOfChildren + "]";
	}

}
