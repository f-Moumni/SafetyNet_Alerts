package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.FireStationNoteFoundException;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;

import lombok.Data;
@Data
@Service
public class FireStationService implements IFireStationService {
	@Autowired
	private FireStationRepository fireStationRepository;

	@Override
	public List<FireStation> findAll() throws DataNotFoundException {
		List<FireStation> firestations = fireStationRepository.findAll();
		if (firestations != null) {
			return firestations;
		} else {
			throw new DataNotFoundException("Firestation Data note found");
		}
	}

	@Override
	public void addFireStation(FireStation fireStation)
			throws AlreadyExistsException {
		FireStation fstation = fireStationRepository
				.FindByAddress(fireStation.getAddress());
		if (fstation != null) {
			throw new AlreadyExistsException("this FireStation "
					+ fireStation.getStation() + " at the address "
					+ fireStation.getAddress() + " is already exsits");
		} else {
			fireStationRepository.addFireStation(fireStation);

		}

	}
	@Override
	public FireStation FindByAdress(String address)
			throws FireStationNoteFoundException {
		FireStation fstation = fireStationRepository.FindByAddress(address);
		if (fstation != null) {
			return fstation;
		} else {
			throw new FireStationNoteFoundException(
					"fire station in address :" + address + " not found");

		}

	}
	@Override
	public FireStation updateFireStation(FireStation fireStation)
			throws FireStationNoteFoundException {
		FireStation fstation = fireStationRepository
				.FindByAddress(fireStation.getAddress());
		if (fstation != null) {
			return fireStationRepository.updateFireStation(fireStation);
		} else {
			throw new FireStationNoteFoundException("the address  :"
					+ fireStation.getAddress() + " cannot be changed");
		}

	}
	@Override
	public FireStation deleteFireStation(String address)
			throws FireStationNoteFoundException {
		FireStation fstationByAdress = fireStationRepository
				.FindByAddress(address);
		if (fstationByAdress != null) {
			fireStationRepository.deleteFireStation(fstationByAdress);
			return fstationByAdress;
		} else {
			throw new FireStationNoteFoundException(
					"firestation at address  :" + address + " not found");
		}

	}

}
