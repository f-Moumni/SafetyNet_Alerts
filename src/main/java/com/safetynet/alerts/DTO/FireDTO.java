package com.safetynet.alerts.DTO;

import java.util.ArrayList;
import java.util.List;
/**
 * FireDTO data transfer object : used to contain the inhabitants and the
 * station that covers an address
 * 
 * @author Fatima
 *
 */
public class FireDTO {
	/**
	 * the address to check
	 */
	private String address;

	/**
	 * the station that covered the address
	 */

	private int station;
	/**
	 * the inhabitants of the address
	 */

	private List<InhabitantDTO> inhabitants = new ArrayList<>();
	/*
	 * empty class constructor
	 */

	public FireDTO() {
	}

	/**
	 * all fields class constructor
	 * 
	 * @param address
	 * @param station
	 * @param inhabitants
	 */
	public FireDTO(String address, int station,
			List<InhabitantDTO> inhabitants) {
		this.address = address;
		this.station = station;
		this.inhabitants = inhabitants;
	}
	/**
	 * Getter of the Fire address.
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * Setter of the Fire address
	 * 
	 * @param address
	 *            to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * Getter of the Fire station.
	 * 
	 * @return station
	 */
	public int getStation() {
		return station;
	}
	/**
	 * Setter of fire station
	 * 
	 * @param station
	 */
	public void setStation(int station) {
		this.station = station;
	}
	/**
	 * Getter of an inhabitants
	 * 
	 * @return inhabitants
	 */
	public List<InhabitantDTO> getInhabitants() {
		return inhabitants;
	}
	/**
	 * Setter of an inhabitants
	 * 
	 * @return inhabitants to set
	 */
	public void setInhabitants(List<InhabitantDTO> inhabitants) {
		this.inhabitants = inhabitants;
	}
	/**
	 * Serialization toString method.
	 */
	@Override
	public String toString() {
		return "FireDTO [address=" + address + ", station=" + station
				+ ", inhabitants=" + inhabitants + "]";
	}

}
