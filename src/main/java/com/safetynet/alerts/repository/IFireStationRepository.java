package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.FireStation;

public interface IFireStationRepository {

	List<FireStation> findAll();

	void addFireStation(FireStation fireStation);

	void deleteFireStation(FireStation fireStation);

	FireStation findByAddress(String address);

	FireStation updateFireStation(FireStation fireStation);

	List<String> findByStation(int station);

}
