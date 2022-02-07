package com.safetynet.alerts.DTO;

import java.util.ArrayList;
import java.util.List;

public class FireDTO {

	private String address;
	private int station;
	private List<InhabitantDTO> inhabitants = new ArrayList<InhabitantDTO>();

	public FireDTO() {

	}

	public FireDTO(String address, int station,
			List<InhabitantDTO> inhabitants) {
		this.address = address;
		this.station = station;
		this.inhabitants = inhabitants;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	public List<InhabitantDTO> getInhabitants() {
		return inhabitants;
	}

	public void setInhabitants(List<InhabitantDTO> inhabitants) {
		this.inhabitants = inhabitants;
	}

	@Override
	public String toString() {
		return "FireDTO [address=" + address + ", station=" + station
				+ ", inhabitants=" + inhabitants + "]";
	}

}
