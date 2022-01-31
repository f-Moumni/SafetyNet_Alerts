package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.IFireStationService;

import lombok.Data;
@Data
@RestController
public class FireStationController {
	private final IFireStationService fireStationService;
	@Autowired

	public FireStationController(IFireStationService fireStationService) {
		this.fireStationService = fireStationService;
	}

	@GetMapping(value = "firestation")
	public ResponseEntity<List<FireStation>> findAll() {
		if (fireStationService.findAll().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(fireStationService.findAll(),
					HttpStatus.OK);
		}
	}

}
