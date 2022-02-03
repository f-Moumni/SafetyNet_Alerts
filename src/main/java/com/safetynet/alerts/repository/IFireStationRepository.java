package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.FireStation;

public interface IFireStationRepository {

	List<FireStation> findAll();

	void addFireStation(FireStation fireStation);

	void deleteFireStation(FireStation fireStation);

	FireStation FindByAddress(String address);

	FireStation updateFireStation(FireStation fireStation);

	FireStation FindByStation(int station);

}
