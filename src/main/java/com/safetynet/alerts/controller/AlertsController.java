package com.safetynet.alerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.DTO.ChildDTO;
import com.safetynet.alerts.exceptions.FireStationNotFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.service.IAlertsService;

@RestController
@ResponseBody
public class AlertsController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AlertsController.class);
	@Autowired
	private IAlertsService alertsService;

	@GetMapping("/firestation")
	public ResponseEntity<Object> getCoveredPopulationByStation(
			@RequestParam int station) throws MedicalRecordNotFoundException,
			FireStationNotFoundException, PersonNotFoundException {
		LOGGER.debug("at get mapping couverd pupulation by station methode ");

		if (station <= 0) {
			LOGGER.error("invalid Station number HttpStatus :{}",
					HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body("Invalid Station number");
		} else {
			LOGGER.info("population covered by station getted   HttpStatus :{}",
					HttpStatus.OK);
			return new ResponseEntity<>(
					alertsService.getPopulationCovredByStation(station),
					HttpStatus.OK);
		}
	}

	@GetMapping("/childAlert")
	public ResponseEntity<Object> getChildenByaddress(
			@RequestParam String address)
			throws MedicalRecordNotFoundException, PersonNotFoundException {
		LOGGER.debug("at get mapping Childen By address methode ");

		if (address.isBlank()) {
			LOGGER.error("invalid address HttpStatus :{}",
					HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body("Invalid address");
		} else {
			List<ChildDTO> childAlert = alertsService
					.getChildrenByAddress(address);
			LOGGER.info(" Childen By address getted HttpStatus :{}",
					HttpStatus.OK);
			return new ResponseEntity<>(childAlert, HttpStatus.OK);
		}
	}
	@GetMapping("/phoneAlert")
	public ResponseEntity<Object> getphonesByStation(int station)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException {
		LOGGER.debug("at get mapping phones By address methode ");

		if (station <= 0) {
			LOGGER.error("invalid Station number  HttpStatus :{}",
					HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body("Invalid Station number");
		}
		LOGGER.info(
				"phone numbers of population covered by station getted   HttpStatus :{}",
				HttpStatus.OK);
		return new ResponseEntity<>(
				alertsService.getPhoneNumberByStation(station), HttpStatus.OK);

	}
	@GetMapping("/fire")
	public ResponseEntity<Object> getInhabitantsByAddress(
			@RequestParam String address) throws MedicalRecordNotFoundException,
			FireStationNotFoundException, PersonNotFoundException {
		LOGGER.debug("at get Inhabitants By Address methode ");
		if (address.isBlank()) {
			LOGGER.error("invalid address HttpStatus :{}",
					HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body("Invalid address");
		} else {
			LOGGER.info(" Inhabitants By Address getted  HttpStatus :{}",
					HttpStatus.OK);
			return new ResponseEntity<>(
					alertsService.getInhabitantByAddress(address),
					HttpStatus.OK);
		}
	}
	@GetMapping("/flood")
	public ResponseEntity<Object> getfloodsByStation(@RequestParam int station)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException {
		LOGGER.debug("at get floods By Station methode ");
		if (station <= 0) {
			LOGGER.error("invalid Station number  HttpStatus :{}",
					HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body("Invalid station number");
		} else {
			LOGGER.info(" floods By Station getted   HttpStatus :{}",
					HttpStatus.OK);
			return new ResponseEntity<>(
					alertsService.getHomesByStation(station), HttpStatus.OK);
		}
	}
	@GetMapping("/personInfo")
	public ResponseEntity<Object> getPersonInfosByName(
			@RequestParam String firstName, @RequestParam String lastName)
			throws MedicalRecordNotFoundException, PersonNotFoundException {
		LOGGER.debug("at Person Infos By Name methode ");
		if (firstName.isBlank() || lastName.isBlank()) {
			LOGGER.error("Invalid name  HttpStatus :{}",
					HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body("Invalid name");
		} else {
			LOGGER.info("Person Infos By Name getted   HttpStatus :{}",
					HttpStatus.OK);
			return new ResponseEntity<>(
					alertsService.getPersonInfosByName(firstName, lastName),
					HttpStatus.OK);
		}
	}

	@GetMapping("/CommunityEmail")
	public ResponseEntity<Object> getCommunityEmails(@RequestParam String city)
			throws PersonNotFoundException {
		LOGGER.debug("at Community Emails methode ");
		if (city.isBlank()) {
			LOGGER.error("invalid city  HttpStatus :{}",
					HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body("Invalid city");
		} else {
			LOGGER.info("Community Emails getted   HttpStatus :{}",
					HttpStatus.OK);
			return new ResponseEntity<>(alertsService.getCommunityEmail(city),
					HttpStatus.OK);
		}
	}
}
