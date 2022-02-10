package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.DTO.FireStationDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.FireStationNoteFoundException;
import com.safetynet.alerts.model.FireStation;

public interface IFireStationService {

	public List<FireStationDTO> findAll() throws DataNotFoundException;

	public void addFireStation(FireStation fireStation)
			throws AlreadyExistsException;

	public FireStationDTO updateFireStation(FireStation fireStation)
			throws FireStationNoteFoundException;

	public FireStationDTO deleteFireStation(String address)
			throws FireStationNoteFoundException;

	public List<String> FindByStation(int station)
			throws FireStationNoteFoundException;

	public FireStation FindByAddress(String address)
			throws FireStationNoteFoundException;

}
