package com.safetynet.alerts.DTO;

import java.util.ArrayList;
import java.util.List;

public class HomeDTO {

	private String address;
	private List<InhabitantDTO> inhabitants = new ArrayList<InhabitantDTO>();

	public HomeDTO() {
		super();
	}

	public HomeDTO(String address, List<InhabitantDTO> inhabitants) {
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
		return "HomeDTO [address=" + address + ", inhabitants=" + inhabitants
				+ "]";
	}

}
