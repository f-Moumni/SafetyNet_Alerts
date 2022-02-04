package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.DTO.FireStationDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.FireStationNoteFoundException;
import com.safetynet.alerts.model.FireStation;

public interface IFireStationService {

	List<FireStationDTO> findAll() throws DataNotFoundException;

	void addFireStation(FireStation fireStation) throws AlreadyExistsException;

	FireStation FindByAdress(String address)
			throws FireStationNoteFoundException;

	FireStationDTO updateFireStation(FireStation fireStation)
			throws FireStationNoteFoundException;

	FireStationDTO deleteFireStation(String address)
			throws FireStationNoteFoundException;

}
