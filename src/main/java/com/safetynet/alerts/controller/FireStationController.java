package com.safetynet.alerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.DTO.FireStationDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.FireStationNotFoundException;
import com.safetynet.alerts.service.IFireStationService;
import com.safetynet.alerts.util.FireStationConverter;
/**
 * FireStationController class, allows to use administrative endpoints for CRUD
 * operations * @author Fatima
 * 
 * @see IFireStationService
 * @see FireStationConverter
 */
@RestController
@ResponseBody
public class FireStationController {
	/**
	 * a SLF4J/LOG4J LOGGER instance
	 */
	private final Logger LOGGER = LoggerFactory
			.getLogger(FireStationController.class);

	/**
	 * IFireStationService the interface of service class to manage fire
	 * stations
	 */
	@Autowired
	private IFireStationService fireStationService;
	/**
	 * firestation object mapper
	 */
	@Autowired
	private FireStationConverter fireStationConverter;
	/**
	 * getFireStations get all fire stations in data
	 * 
	 * @return list of fire stations
	 * @throws DataNotFoundException
	 */
	@GetMapping("/firestations")
	public ResponseEntity<List<FireStationDTO>> getFireStations()
			throws DataNotFoundException {
		LOGGER.debug("at get FireStations methode ");
		List<FireStationDTO> fireStations = fireStationService.findAll();
		LOGGER.info("fireStations list getted with success   HttpStatus :{}",
				HttpStatus.OK);
		return new ResponseEntity<>(fireStations, HttpStatus.OK);

	}
	/**
	 * addFireStation save a given fire station
	 * 
	 * @param fireStation
	 *            fire station to save
	 * @return the fire station saved
	 * @throws AlreadyExistsException
	 */
	@PostMapping("/firestation")
	public ResponseEntity<Object> addFireStation(
			@RequestBody FireStationDTO fireStation)
			throws AlreadyExistsException {
		LOGGER.debug("at addFireStation methode ");
		if ((fireStation.getStation() <= 0)
				|| (fireStation.getAddress().isBlank())) {
			LOGGER.error(
					"Invalid fire station number or address HttpStatus :{}",
					HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest()
					.body("Invalid fire station number or address");
		} else {
			FireStationDTO fireStationSaved = fireStationService.addFireStation(
					fireStationConverter.toFireStation(fireStation));
			LOGGER.info(
					"fireStation {} at the address{} saved with success   HttpStatus :{}",
					fireStation.getStation(), fireStation.getAddress(),
					HttpStatus.CREATED);
			return new ResponseEntity<>(fireStationSaved, HttpStatus.CREATED);
		}
	}
	/**
	 * updateFireStation allow to update a given fire station
	 * 
	 * @param fireStation
	 *            fire station to update
	 * @return fire station updated or error message if not saved
	 * @throws FireStationNotFoundException
	 */
	@PutMapping("/firestation")
	public ResponseEntity<Object> updateFireStation(
			@RequestBody FireStationDTO fireStation)
			throws FireStationNotFoundException {
		LOGGER.debug("at updateFireStation methode ");
		if ((fireStation.getStation() <= 0)
				|| (fireStation.getAddress().isBlank())) {
			LOGGER.error(
					"Invalid fire station number or address HttpStatus :{}",
					HttpStatus.NO_CONTENT);
			return ResponseEntity.badRequest()
					.body("Invalid fire station number or address");
		}
		FireStationDTO fStation = fireStationService.updateFireStation(
				fireStationConverter.toFireStation(fireStation));
		LOGGER.info(
				"fireStation {} at the address{} updated with success   HttpStatus :{}",
				fireStation.getStation(), fireStation.getAddress(),
				HttpStatus.OK);
		return new ResponseEntity<>(fStation, HttpStatus.OK);
	}
	/**
	 * deleteFireStation allow to delete fire station at a given address
	 * 
	 * @param address
	 *            the fire stations address
	 * @return a fire station deleted
	 * @throws FireStationNotFoundException
	 */
	@DeleteMapping("/firestation")
	public ResponseEntity<Object> deleteFireStation(
			@RequestParam String address) throws FireStationNotFoundException {
		LOGGER.debug("at deleteFireStation methode ");
		if (address.isBlank()) {
			LOGGER.error("Invalid fire station address HttpStatus :{}",
					HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body("Invalid address");
		}
		FireStationDTO fStation = fireStationService.deleteFireStation(address);
		LOGGER.info(
				"fireStation {} at the address{} deleted with success   HttpStatus :{}",
				fStation.getStation(), fStation.getAddress(), HttpStatus.OK);
		return new ResponseEntity<>(fStation, HttpStatus.OK);
	}
}
