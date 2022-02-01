package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.FireStation;

public interface IFireStationRepository {

	List<FireStation> findAll();

	void addFireStation(FireStation fireStation);

}
