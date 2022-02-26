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
import com.safetynet.alerts.DTO.FireDTO;
import com.safetynet.alerts.DTO.HomeDTO;
import com.safetynet.alerts.DTO.PersonInfosDTO;
import com.safetynet.alerts.exceptions.FireStationNotFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.service.AlertService;
import com.safetynet.alerts.service.IAlertsService;
/**
 * AlertesController: used to provide endpoints for alert urls
 * 
 * @author Fatima
 * @see AlertService
 */
@RestController
@ResponseBody
public class AlertsController {
	/**
	 * a SLF4J/LOG4J LOGGER instance
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AlertsController.class);
	/**
	 * IAlertsService the interface of AlertsService class which processes the
	 * data
	 */
	@Autowired
	private IAlertsService alertsService;

	/**
	 * getCoveredPopulationByStation Get request to get all persons covered by
	 * station number with count of adults and children number
	 * 
	 * @param station
	 *            :the station number that covers population
	 * 
	 * @return ResponseEntity of CovredPopulationDTO object or an error message
	 *         in case of error
	 * @see CovredPopulationDTO
	 * @throws MedicalRecordNotFoundException
	 * @throws FireStationNotFoundException
	 * @throws PersonNotFoundException
	 */
	@GetMapping("/firestation")
	public ResponseEntity<Object> getCoveredPopulationByStation(
			@RequestParam int station) throws MedicalRecordNotFoundException,
			FireStationNotFoundException, PersonNotFoundException {
		LOGGER.debug(
				"Get mapping couverd pupulation by station methode for station {} ",
				station);

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
	/**
	 * getChildenByaddress GET request to get all children living in the address
	 * 
	 * @param address
	 * @return ResponseEntity of list of ChildDTO object or an error message in
	 *         case of error
	 * @see ChildDTO
	 * @throws MedicalRecordNotFoundException
	 * @throws PersonNotFoundException
	 */
	@GetMapping("/childAlert")
	public ResponseEntity<Object> getChildenByaddress(
			@RequestParam String address)
			throws MedicalRecordNotFoundException, PersonNotFoundException {
		LOGGER.debug("Get mapping Childen By address for address {} ", address);

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
	/**
	 * getphonesByStation GET request to get phone numbers of population covered
	 * by station number
	 * 
	 * @param station
	 *            number
	 * @return ResponseEntity of list phone numbers or an error message in case
	 *         of error
	 * @throws MedicalRecordNotFoundException
	 * @throws FireStationNotFoundException
	 * @throws PersonNotFoundException
	 */
	@GetMapping("/phoneAlert")
	public ResponseEntity<Object> getphonesByStation(int station)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException {
		LOGGER.debug("Get mapping phones By station for station {} ", station);

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
	/**
	 * getInhabitantsByAddress GET request to get all the inhabitants of given
	 * address
	 * 
	 * @param address
	 * @return ResponseEntity of FireDTO object or an error message in case of
	 *         error
	 * @see FireDTO
	 * @throws MedicalRecordNotFoundException
	 * @throws FireStationNotFoundException
	 * @throws PersonNotFoundException
	 */
	@GetMapping("/fire")
	public ResponseEntity<Object> getInhabitantsByAddress(
			@RequestParam String address) throws MedicalRecordNotFoundException,
			FireStationNotFoundException, PersonNotFoundException {
		LOGGER.debug("Get mapping Inhabitants By Address for address {} ",
				address);
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
	/**
	 * getHomesByStation GET request to get all homes covered by a station
	 * 
	 * @param station
	 * @return ResponseEntity of list of HomeDTO object or an error message in
	 *         case of error
	 * @see HomeDTO
	 * @throws MedicalRecordNotFoundException
	 * @throws FireStationNotFoundException
	 * @throws PersonNotFoundException
	 */
	@GetMapping("/flood")
	public ResponseEntity<Object> getHomesByStation(@RequestParam int station)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException {
		LOGGER.debug("Get mapping floods By Station for station {} ", station);
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
	/**
	 * getPersonInfosByName GET request to get persons information with the
	 * given name
	 * 
	 * @param firstName
	 * @param lastName
	 * @return ResponseEntity of list of PersonInfosDTO object or an error
	 *         message in case of error
	 * @see PersonInfosDTO
	 * @throws MedicalRecordNotFoundException
	 * @throws PersonNotFoundException
	 */
	@GetMapping("/personInfo")
	public ResponseEntity<Object> getPersonInfosByName(
			@RequestParam String firstName, @RequestParam String lastName)
			throws MedicalRecordNotFoundException, PersonNotFoundException {
		LOGGER.debug("Get mapping Person Infos By Name for {} {} ", firstName,
				lastName);
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
	/**
	 * getCommunityEmails GET request to obtain all the e-mail addresses of a
	 * community in a given city
	 * 
	 * @param city
	 * @return ResponseEntity of list of e-mail addresses or an error message in
	 *         case of error
	 * @throws PersonNotFoundException
	 */
	@GetMapping("/CommunityEmail")
	public ResponseEntity<Object> getCommunityEmails(@RequestParam String city)
			throws PersonNotFoundException {
		LOGGER.debug("Get mapping Community Emails for city: {} ", city);
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
