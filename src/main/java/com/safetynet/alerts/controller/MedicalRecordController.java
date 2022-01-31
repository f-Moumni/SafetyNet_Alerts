package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;

import lombok.Data;
@Data
@RestController
public class MedicalRecordController {
	private final IMedicalRecordService medicalRecordService;

	@Autowired
	public MedicalRecordController(
			final IMedicalRecordService medicalRecordService) {
		this.medicalRecordService = medicalRecordService;
	}

	@GetMapping(value = "medicalRecord")
	public ResponseEntity<List<MedicalRecord>> getPersons() {
		if (medicalRecordService.findAll().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(medicalRecordService.findAll(),
					HttpStatus.OK);
		}
	}

}
