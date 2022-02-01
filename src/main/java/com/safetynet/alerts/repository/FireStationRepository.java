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
}
