package com.safetynet.alerts.DTO;

import java.util.ArrayList;
import java.util.List;

public class FloodDTO {

	private String address;
	private List<InhabitantDTO> inhabitants = new ArrayList<InhabitantDTO>();

	public FloodDTO() {
		super();
	}

	public FloodDTO(String address, List<InhabitantDTO> inhabitants) {
		super();
		this.address = address;
		this.inhabitants = inhabitants;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<InhabitantDTO> getInhabitants() {
		return inhabitants;
	}

	public void setInhabitants(List<InhabitantDTO> inhabitants) {
		this.inhabitants = inhabitants;
	}

	@Override
	public String toString() {
		return "FloodDTO [address=" + address + ", inhabitants=" + inhabitants
				+ "]";
	}

}
