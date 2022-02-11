package com.safetynet.alerts.model;

import java.util.Objects;

public class FireStation {

	private String address;
	private int station;

	public FireStation(String address, int station) {

		this.address = address;
		this.station = station;
	}

	public FireStation() {

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FireStation other = (FireStation) obj;
		return Objects.equals(address, other.address)
				&& station == other.station;
	}

	@Override
	public String toString() {
		return "FireStation [address=" + address + ", station=" + station + "]";
	}

}
