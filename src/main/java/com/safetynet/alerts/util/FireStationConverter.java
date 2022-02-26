package com.safetynet.alerts.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.safetynet.alerts.DTO.FireStationDTO;
import com.safetynet.alerts.model.FireStation;

/**
 * FireStationConverter FireStation mapper
 * 
 * @author Fatima
 *
 */
@Component
public class FireStationConverter {

	/**
	 * Create a SLF4J/LOG4J LOGGER instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(FireStationConverter.class);
	/**
	 * toFireStationDTO map a given FireStation to FireStationDTO Object
	 * 
	 * @param fireStation
	 * @return fireStationDTO
	 */
	public FireStationDTO toFireStationDTO(FireStation fireStation) {
		LOGGER.debug("mapping to FireStationDTO for FireStation {}",
				fireStation);
		return new FireStationDTO(fireStation.getAddress(),
				fireStation.getStation());
	}
	/**
	 * toFireStation map a given FireStationDTO to FireStation Object
	 * 
	 * @param fireStationDTO
	 * @return FireStation
	 */
	public FireStation toFireStation(FireStationDTO fireStation) {
		LOGGER.debug("mapping to FireStation for FireStationDTO {}",
				fireStation);
		return new FireStation(fireStation.getAddress(),
				fireStation.getStation());
	}
	/**
	 * toFireStationDTOList map a given FireStation objects list to
	 * FireStationDTO Object list
	 * 
	 * @param list
	 *            of FireStation
	 * @return list of FireStationDTO
	 */
	public List<FireStationDTO> toFireStationDTOList(
			List<FireStation> fireStations) {
		LOGGER.debug("mapping to list of FireStationDTO for FireStations {}",
				fireStations);
		List<FireStationDTO> FireStationsDTO = new ArrayList<FireStationDTO>();
		for (FireStation fireStation : fireStations) {
			FireStationsDTO.add(toFireStationDTO(fireStation));

		}
		return FireStationsDTO;
	}

}
