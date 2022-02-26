package com.safetynet.alerts.DTO;

import com.safetynet.alerts.model.FireStation;

/**
 * FireStationDTO data transfer object: used to contain FireStation
 * 
 * @see FireStation
 * @author Fatima
 *
 */
public class FireStationDTO {

	/**
	 * address covered by fire station
	 */
	private String address;

	/**
	 * fire station number
	 */
	private int station;

	/**
	 * all fields constructor
	 * 
	 * @param address
	 *            covered address
	 * @param station
	 *            fire station number
	 */
	public FireStationDTO(String address, int station) {
		this.address = address;
		this.station = station;
	}

	/**
	 * empty class constructor
	 */
	public FireStationDTO() {
	}

	/**
	 * Getter of Firestation address
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter of FireStation address
	 * 
	 * @param address
	 *            to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Getter of fire station number
	 * 
	 * @return station number
	 */
	public int getStation() {
		return station;
	}

	/**
	 * Setter of fire station number
	 * 
	 * @param station
	 *            number to set
	 */
	public void setStation(int station) {
		this.station = station;
	}
	/**
	 * Serialization toString method.
	 */
	@Override
	public String toString() {
		return "FireStation [address=" + address + ", station=" + station + "]";
	}
}
