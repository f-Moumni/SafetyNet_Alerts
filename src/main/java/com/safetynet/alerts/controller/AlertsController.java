package com.safetynet.alerts.controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.DTO.ChildAlertDTO;
import com.safetynet.alerts.DTO.CoveredPopulationDTO;
import com.safetynet.alerts.DTO.FireDTO;
import com.safetynet.alerts.DTO.FloodDTO;
import com.safetynet.alerts.DTO.PersonInfosDTO;
import com.safetynet.alerts.exceptions.BadRequestException;
import com.safetynet.alerts.service.IAlertsService;

@RestController
public class AlertsController {
	private final IAlertsService alertsService;

	@Autowired
	public AlertsController(IAlertsService alertsService) {
		this.alertsService = alertsService;
	}

	@GetMapping("/firestationAlert")
	public ResponseEntity<CoveredPopulationDTO> getCoveredPopulationByStation(
			@RequestParam int station) throws BadRequestException {
		String nbrString = String.valueOf(station);
		if (nbrString.equals(null) || nbrString.isBlank()) {
			throw new BadRequestException("Station number is required");
		} else
			return new ResponseEntity<>(
					alertsService.getPopulationCovredByStation(station),
					HttpStatus.OK);

	}

	@GetMapping("/childAlert")
	public ResponseEntity<ChildAlertDTO> getChildenByaddress(
			@RequestParam String address) throws BadRequestException {

		if (address.equals(null) || address.isBlank()) {
			throw new BadRequestException("address is required");
		} else
			return new ResponseEntity<>(
					alertsService.getChildrenByAddress(address), HttpStatus.OK);

	}
	@GetMapping("/phoneAlert")
	public ResponseEntity<HashSet<String>> getphonesByaddress(
			@RequestParam int station) throws BadRequestException {

		return new ResponseEntity<>(
				alertsService.getPhoneNumberByAddress(station), HttpStatus.OK);

	}
	@GetMapping("/fire")
	public ResponseEntity<FireDTO> getInhabitantsByAddress(
			@RequestParam String address) {
		return new ResponseEntity<>(
				alertsService.getInhabitantByAddress(address), HttpStatus.OK);

	}
	@GetMapping("/flood")
	public ResponseEntity<List<FloodDTO>> getfloodsByStation(
			@RequestParam int station) {
		return new ResponseEntity<>(alertsService.getFloodsByStation(station),
				HttpStatus.OK);

	}
	@GetMapping("/personInfo")
	public ResponseEntity<List<PersonInfosDTO>> getPersonInfosByName(
			@RequestParam String firstName, @RequestParam String lastName) {
		return new ResponseEntity<>(
				alertsService.getPersonInfosByName(firstName, lastName),
				HttpStatus.OK);

	}

	@GetMapping("/CommunityEmail")
	public ResponseEntity<HashSet<String>> getCommunityEmail(
			@RequestParam String city) {
		return new ResponseEntity<>(alertsService.getCommunityEmail(city),
				HttpStatus.OK);

	}
}
