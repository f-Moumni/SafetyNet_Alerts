package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.FireStation;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Data
@Repository
public class FireStationRepository implements IFireStationRepository {

	private List<FireStation> fireStations = new ArrayList<>();

	@Override
	public List<FireStation> findAll() {
		return fireStations;
	}
	@Override
	public void addFireStation(FireStation fireStation) {
		fireStations.add(fireStation);

	}
	@Override
	public void deleteFireStation(FireStation fireStation) {
		fireStations.remove(fireStation);

	}
	@Override
	public FireStation FindByAddress(String address) {
		FireStation fireStation = null;
		for (FireStation fStation : fireStations) {
			if (fStation.getAddress().equalsIgnoreCase(address)) {
				fireStation = fStation;
				break;
			}
		}
		return fireStation;

	}
	@Override
	public List<String> FindByStation(int station) {
		List<String> addresses = new ArrayList<>();
		for (FireStation fStation : fireStations) {
			if (fStation.getStation() == station) {
				addresses.add(fStation.getAddress());
			}
		}
		return addresses;

	}
	@Override
	public FireStation updateFireStation(FireStation fireStation) {
		FireStation fStation = FindByAddress(fireStation.getAddress());
		deleteFireStation(fStation);
		addFireStation(fireStation);
		return fireStation;
	}

}
