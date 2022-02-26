package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.data.ReadDataFromJson;
import com.safetynet.alerts.model.FireStation;
/**
 * FireStationRepository class allows to store and manage fire station data
 * 
 * @see IFireStationRepository
 * @see ReadDataFromJson
 * @author Fatima
 *
 */
@Repository
public class FireStationRepository implements IFireStationRepository {
	/**
	 * Create a SLF4J/LOG4J LOGGER instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(FireStationRepository.class);

	/**
	 * FireStation data list
	 */
	private List<FireStation> fireStations = new ArrayList<>();
	/**
	 * a class Constructor
	 * 
	 * @param fireStations
	 */
	@Autowired
	public FireStationRepository(List<FireStation> fireStations) {
		this.fireStations = fireStations;
	}

	/**
	 * findAll to get all fire stations
	 * 
	 * @return list of fireStation
	 */
	@Override
	public List<FireStation> findAll() {
		LOGGER.debug("getting of all fireStations in data");
		return fireStations;
	}

	/**
	 * addFireStation to save a given fire station
	 * 
	 * @param fireStation
	 *            to save
	 */
	@Override
	public void addFireStation(FireStation fireStation) {
		LOGGER.debug("saving fireStation {}", fireStation);
		fireStations.add(fireStation);

	}

	/**
	 * delete FireStation to delete a given fire station
	 * 
	 * @param fireStation
	 *            to delete
	 */
	@Override
	public void deleteFireStation(FireStation fireStation) {
		LOGGER.debug("deleting fireStation {}", fireStation);
		fireStations.remove(fireStation);

	}

	/**
	 * findByAddress to get Fire station for a given address
	 * 
	 * @param address
	 * 
	 */
	@Override
	public FireStation findByAddress(String address) {
		LOGGER.debug("search for fireStation that couvred {}", address);
		return fireStations.stream().filter(fireStation -> fireStation
				.getAddress().equalsIgnoreCase(address)).findFirst()
				.orElse(null);
	}

	/**
	 * findByStation to get address covered by a given station
	 * 
	 * @param station
	 */
	@Override
	public List<String> findByStation(int station) {
		LOGGER.debug("search for all addresses couvred by station number{}",
				station);
		return fireStations.stream()
				.filter(fireStation -> fireStation.getStation() == station)
				.map(FireStation::getAddress).collect(Collectors.toList());

	}

	/**
	 * update a given firestation
	 * 
	 * @param fireStation
	 *            to update
	 */
	@Override
	public FireStation updateFireStation(FireStation fireStation) {
		LOGGER.debug("updating fireStation {}", fireStation);
		FireStation fStation = findByAddress(fireStation.getAddress());
		deleteFireStation(fStation);
		addFireStation(fireStation);
		return fireStation;
	}

}
