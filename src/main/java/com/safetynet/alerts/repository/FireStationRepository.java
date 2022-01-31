package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.data.IReadData;
import com.safetynet.alerts.model.FireStation;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Data
@Repository
public class FireStationRepository {
	private List<FireStation> fireStations = new ArrayList<>();
	private final IReadData readData;
	@Autowired
	public FireStationRepository(final IReadData readData) {
		log.debug("getting FireStations list in respository");
		this.readData = readData;
		fireStations.addAll(readData.getListFireStations());
	}

	public List<FireStation> findAll() {
		return fireStations;
	}
}
