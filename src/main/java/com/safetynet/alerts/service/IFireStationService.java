package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.DTO.FireStationDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.FireStationNotFoundException;
import com.safetynet.alerts.model.FireStation;

public interface IFireStationService {

	public List<FireStationDTO> findAll() throws DataNotFoundException;

	public void addFireStation(FireStation fireStation)
			throws AlreadyExistsException;

	public FireStationDTO updateFireStation(FireStation fireStation)
			throws FireStationNotFoundException;

	public FireStationDTO deleteFireStation(String address)
			throws FireStationNotFoundException;

	public List<String> FindByStation(int station)
			throws FireStationNotFoundException;

	public FireStation FindByAddress(String address)
			throws FireStationNotFoundException;

}
