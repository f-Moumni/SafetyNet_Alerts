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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.DTO.FireStationDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.BadRequestException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.FireStationNoteFoundException;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.IFireStationService;
import com.safetynet.alerts.util.FireStationConverter;

@RestController
@RequestMapping("/firestation")
public class FireStationController {
	private final Logger LOGGER = LoggerFactory
			.getLogger(FireStationController.class);

	private final IFireStationService fireStationService;

	@Autowired
	public FireStationController(IFireStationService fireStationService) {
		this.fireStationService = fireStationService;
	}
	@Autowired
	FireStationConverter fireStationConverter;
	@GetMapping
	public ResponseEntity<List<FireStationDTO>> findAll()
			throws DataNotFoundException {
		return new ResponseEntity<>(fireStationService.findAll(),
				HttpStatus.OK);

	}
	@PostMapping
	public ResponseEntity<FireStationDTO> addFireStation(
			@RequestBody FireStation fireStation)
			throws AlreadyExistsException, BadRequestException {
		if ((fireStation.getStation() > 0)
				&& (!fireStation.getAddress().isBlank())) {
			fireStationService.addFireStation(fireStation);
			return new ResponseEntity<>(
					fireStationConverter.toFireStationDTO(fireStation),
					HttpStatus.CREATED);
		} else {
			throw new BadRequestException(
					"All fields (fire station numero and adresse) are required ");
		}
	}
	@PutMapping
	public ResponseEntity<FireStationDTO> updateFireStation(
			@RequestBody FireStation fireStation)
			throws BadRequestException, FireStationNoteFoundException {
		if ((fireStation.getStation() > 0)
				&& (!fireStation.getAddress().isBlank())) {
			return new ResponseEntity<>(
					fireStationService.updateFireStation(fireStation),
					HttpStatus.OK);
		} else {
			throw new BadRequestException(
					"All fields (fire station numero and adresse) are required ");
		}
	}
	@DeleteMapping
	public ResponseEntity<FireStationDTO> deleteFireStation(
			@RequestParam String address)
			throws BadRequestException, FireStationNoteFoundException {
		if (!address.isBlank()) {

			return new ResponseEntity<>(
					fireStationService.deleteFireStation(address),
					HttpStatus.OK);
		} else {
			throw new BadRequestException(
					"All fields (fire station numero and adresse) are required ");
		}
	}
}
