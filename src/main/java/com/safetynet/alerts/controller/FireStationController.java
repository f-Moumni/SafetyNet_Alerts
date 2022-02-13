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
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.DTO.FireStationDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.FireStationNotFoundException;
import com.safetynet.alerts.service.IFireStationService;
import com.safetynet.alerts.util.FireStationConverter;

@RestController

public class FireStationController {
	private final Logger LOGGER = LoggerFactory
			.getLogger(FireStationController.class);
	@Autowired
	private IFireStationService fireStationService;
	@Autowired
	private FireStationConverter fireStationConverter;

	@GetMapping("/firestations")
	public ResponseEntity<List<FireStationDTO>> getFireStations()
			throws DataNotFoundException {
		LOGGER.debug("at get FireStations methode ");
		List<FireStationDTO> fireStations = fireStationService.findAll();
		LOGGER.info("fireStations list getted with success   HttpStatus :{}",
				HttpStatus.OK);
		return new ResponseEntity<>(fireStations, HttpStatus.OK);

	}

	@PostMapping("/firestation")
	public ResponseEntity<?> addFireStation(
			@RequestBody FireStationDTO fireStation)
			throws AlreadyExistsException {
		LOGGER.debug("at addFireStation methode ");
		if ((fireStation.getStation() <= 0)
				|| (fireStation.getAddress().isBlank())) {
			LOGGER.error(
					"Invalid fire station numero or adresse HttpStatus :{}",
					HttpStatus.NO_CONTENT);
			return ResponseEntity.badRequest()
					.body("Invalid fire station numero or adresse");
		}
		fireStationService.addFireStation(
				fireStationConverter.toFireStation(fireStation));
		LOGGER.info(
				"fireStation {} at the address{} saved with success   HttpStatus :{}",
				fireStation.getStation(), fireStation.getAddress(),
				HttpStatus.CREATED);
		return new ResponseEntity<>(fireStation, HttpStatus.CREATED);

	}
	@PutMapping("/firestation")
	public ResponseEntity<?> updateFireStation(
			@RequestBody FireStationDTO fireStation)
			throws FireStationNotFoundException {
		LOGGER.debug("at updateFireStation methode ");
		if ((fireStation.getStation() <= 0)
				|| (fireStation.getAddress().isBlank())) {
			LOGGER.error(
					"Invalid fire station numero or adresse HttpStatus :{}",
					HttpStatus.NO_CONTENT);
			return ResponseEntity.badRequest()
					.body("Invalid fire station numero or adresse");
		}
		FireStationDTO fStation = fireStationService.updateFireStation(
				fireStationConverter.toFireStation(fireStation));
		LOGGER.info(
				"fireStation {} at the address{} updated with success   HttpStatus :{}",
				fireStation.getStation(), fireStation.getAddress(),
				HttpStatus.OK);
		return new ResponseEntity<>(fStation, HttpStatus.OK);
	}
	@DeleteMapping("/firestation")
	public ResponseEntity<?> deleteFireStation(@RequestParam String address)
			throws FireStationNotFoundException {
		LOGGER.debug("at deleteFireStation methode ");
		if ((address.isBlank()) || (address.equals(null))) {
			LOGGER.error("Invalid fire station adresse HttpStatus :{}",
					HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest()
					.body("Invalid fire station numero or adresse");
		}
		FireStationDTO fStation = fireStationService.deleteFireStation(address);
		LOGGER.info(
				"fireStation {} at the address{} deleted with success   HttpStatus :{}",
				fStation.getStation(), fStation.getAddress(), HttpStatus.OK);
		return new ResponseEntity<>(fStation, HttpStatus.OK);
	}
}
