package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.FireStation;

@Repository
public class FireStationRepository implements IFireStationRepository {

	private List<FireStation> fireStations = new ArrayList<>();

	@Autowired
	public FireStationRepository(List<FireStation> fireStations) {
		this.fireStations = fireStations;
	}
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
	public FireStation findByAddress(String address) {
		return fireStations.stream().filter(fireStation -> fireStation
				.getAddress().equalsIgnoreCase(address)).findFirst()
				.orElse(null);
	}
	@Override
	public List<String> findByStation(int station) {
		return fireStations.stream()
				.filter(fireStation -> fireStation.getStation() == station)
				.map(FireStation::getAddress).collect(Collectors.toList());

	}
	@Override
	public FireStation updateFireStation(FireStation fireStation) {
		FireStation fStation = findByAddress(fireStation.getAddress());
		deleteFireStation(fStation);
		addFireStation(fireStation);
		return fireStation;
	}

}
