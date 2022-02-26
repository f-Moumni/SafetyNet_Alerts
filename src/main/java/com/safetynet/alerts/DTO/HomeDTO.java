package com.safetynet.alerts.DTO;

import java.util.ArrayList;
import java.util.List;
/**
 * HomeDTO data transfer object :used to contain the address of a home and its
 * inhabitants
 * 
 * @author Fatima
 *
 */
public class HomeDTO {
	/**
	 * address of the home
	 */
	private String address;
	/**
	 * the home's inhabitants
	 */
	private List<InhabitantDTO> inhabitants = new ArrayList<InhabitantDTO>();
	/**
	 * empty class constructor
	 */
	public HomeDTO() {
	}
	/**
	 * all fields class constructor
	 * 
	 * @param address
	 * @param inhabitants
	 */
	public HomeDTO(String address, List<InhabitantDTO> inhabitants) {
		this.address = address;
		this.inhabitants = inhabitants;
	}
	/**
	 * Getter of home address
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * Setter of home address
	 * 
	 * @param address
	 *            to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * Getter of the home's inhabitants
	 * 
	 * @return inhabitants
	 */
	public List<InhabitantDTO> getInhabitants() {
		return inhabitants;
	}
	/**
	 * Setter of the home's inhabitants
	 * 
	 * @param inhabitants
	 *            to set
	 */
	public void setInhabitants(List<InhabitantDTO> inhabitants) {
		this.inhabitants = inhabitants;
	}
	/**
	 * Serialization toString method.
	 */
	@Override
	public String toString() {
		return "HomeDTO [address=" + address + ", inhabitants=" + inhabitants
				+ "]";
	}

}
