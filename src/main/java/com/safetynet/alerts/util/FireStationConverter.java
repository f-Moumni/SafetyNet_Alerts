package com.safetynet.alerts.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.safetynet.alerts.DTO.FireStationDTO;
import com.safetynet.alerts.model.FireStation;
@Component
public class FireStationConverter {

	public FireStationDTO toFireStationDTO(FireStation fireStation) {
		return new FireStationDTO(fireStation.getAddress(),
				fireStation.getStation());
	}
	public FireStation toFireStation(FireStationDTO fireStation) {
		return new FireStation(fireStation.getAddress(),
				fireStation.getStation());
	}

	public List<FireStationDTO> toFireStationDTOList(
			List<FireStation> fireStations) {
		List<FireStationDTO> FireStationsDTO = new ArrayList<FireStationDTO>();
		for (FireStation fireStation : fireStations) {
			FireStationsDTO.add(toFireStationDTO(fireStation));

		}
		return FireStationsDTO;
	}

}
