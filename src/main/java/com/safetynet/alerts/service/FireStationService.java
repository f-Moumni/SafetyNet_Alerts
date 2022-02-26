package com.safetynet.alerts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.DTO.FireStationDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.FireStationNotFoundException;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.IFireStationRepository;
import com.safetynet.alerts.util.FireStationConverter;
/**
 * FireStationService class for business processing of fire station CRUD
 * operations
 * 
 * @see IFireStationService
 * @author fatima
 *
 */
@Service
public class FireStationService implements IFireStationService {
	/**
	 * Create a SLF4J/LOG4J LOGGER instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(FireStationService.class);

	/**
	 * IFireStationRepository's implement class reference.
	 */
	@Autowired
	private IFireStationRepository fireStationRepository;

	/**
	 * FireStationConverter fire station mapper
	 */
	@Autowired
	FireStationConverter fireStationConverter;

	/**
	 * to get all fire stations in data
	 * 
	 * @return list of fireStation
	 * @throws DataNotFoundException
	 */
	@Override
	public List<FireStationDTO> findAll() throws DataNotFoundException {
		LOGGER.debug(" find All fire stations start");
		LOGGER.info(" getting all fire stations ");
		List<FireStation> firestations = fireStationRepository.findAll();
		if (firestations != null) {
			return fireStationConverter.toFireStationDTOList(firestations);
		} else {
			LOGGER.error("Firestation Data note found");
			throw new DataNotFoundException("Firestation Data note found");
		}
	}
	/**
	 * addFireStation save a given fire station
	 * 
	 * @param fireStation
	 *            to save
	 * @return fire station saved
	 * @throws AlreadyExistsException
	 */
	@Override
	public FireStationDTO addFireStation(FireStation fireStation)
			throws AlreadyExistsException {
		LOGGER.debug("add fire station stard");
		LOGGER.info("saving of fireStation {}", fireStation);
		FireStation fstation = fireStationRepository
				.findByAddress(fireStation.getAddress());
		if (fstation != null) {
			LOGGER.error(
					"this FireStation {} at the address {} is already exsits",
					fstation.getStation(), fstation.getAddress());
			throw new AlreadyExistsException("this FireStation "
					+ fstation.getStation() + " at the address "
					+ fstation.getAddress() + " is already exsits");
		} else {
			fireStationRepository.addFireStation(fireStation);
			return fireStationConverter.toFireStationDTO(fireStation);
		}

	}

	/**
	 * findByAddress get a fireStation that covers a given address
	 * 
	 * @param address
	 * @return fireStation covered
	 * @throws FireStationNotFoundException
	 */
	@Override
	public FireStation findByAddress(String address)
			throws FireStationNotFoundException {
		LOGGER.debug("find by address start");
		LOGGER.info("find by address {}", address);
		FireStation fstation = fireStationRepository.findByAddress(address);
		if (fstation != null) {
			return fstation;
		} else {
			LOGGER.error("firestation at address  :{} not found", address);
			throw new FireStationNotFoundException(
					"firestation at address :" + address + " not found");
		}

	}

	/**
	 * updateFireStation update a given fire station
	 * 
	 * @param fireStation
	 *            to update
	 * @return fireStation updated
	 * @throws FireStationNotFoundException
	 */
	@Override
	public FireStationDTO updateFireStation(FireStation fireStation)
			throws FireStationNotFoundException {
		LOGGER.debug("updating fire station start");
		LOGGER.info("updating fire station {}", fireStation);
		FireStation fstation = fireStationRepository
				.findByAddress(fireStation.getAddress());
		if (fstation != null) {
			return fireStationConverter.toFireStationDTO(
					fireStationRepository.updateFireStation(fireStation));
		} else {
			LOGGER.error("the address :{} cannot be changed",
					fireStation.getAddress());
			throw new FireStationNotFoundException("the address  :"
					+ fireStation.getAddress() + " cannot be changed");
		}

	}

	/**
	 * deleteFireStation delete a fire station at given address
	 * 
	 * @param address
	 *            covered by fire station
	 * @return fire station deleted
	 * @throws FireStationNotFoundException
	 */
	@Override
	public FireStationDTO deleteFireStation(String address)
			throws FireStationNotFoundException {
		LOGGER.debug("deleting fire station start");
		LOGGER.info("deleting fire station at address {}", address);
		FireStation fstation = fireStationRepository.findByAddress(address);
		if (fstation != null) {
			fireStationRepository.deleteFireStation(fstation);
			return fireStationConverter.toFireStationDTO(fstation);
		} else {
			LOGGER.error("firestation at address  :{} not found", address);
			throw new FireStationNotFoundException(
					"firestation at address  :" + address + " not found");
		}

	}
	/**
	 * findByStation get all addresses covered by given firStation number
	 * 
	 * @param station
	 *            fire station number
	 * @return list of address
	 * @throws FireStationNotFoundException
	 */
	@Override
	public List<String> findByStation(int station)
			throws FireStationNotFoundException {
		LOGGER.debug("find by station start");
		LOGGER.info("find by station {}", station);
		List<String> addresses = fireStationRepository.findByStation(station);
		if (!addresses.isEmpty()) {
			return addresses;
		} else {
			LOGGER.error("firestation {} not found", station);
			throw new FireStationNotFoundException(
					"firestation " + station + " not found");
		}

	}

}
