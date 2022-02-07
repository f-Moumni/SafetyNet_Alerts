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

import com.safetynet.alerts.DTO.MedicalRecordDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;
import com.safetynet.alerts.util.MedicalRecordConverter;

@RestController
@RequestMapping("medicalRecord")
public class MedicalRecordController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MedicalRecordController.class);
	private final IMedicalRecordService medicalRecordService;

	@Autowired
	public MedicalRecordController(
			final IMedicalRecordService medicalRecordService) {
		this.medicalRecordService = medicalRecordService;
	}
	@Autowired
	MedicalRecordConverter medicalRecordConverter;

	@GetMapping
	public ResponseEntity<List<MedicalRecordDTO>> getMedicalRecords()
			throws DataNotFoundException {
		LOGGER.debug("at getMedicalRecords methode ");
		List<MedicalRecordDTO> medicalRecordList = medicalRecordService
				.findAll();
		LOGGER.info("MedicalRecords list getted with success   HttpStatus :{}",
				HttpStatus.OK);
		return new ResponseEntity<>(medicalRecordList, HttpStatus.OK);

	}
	@PostMapping
	public ResponseEntity<?> addMedicalRecord(
			@RequestBody MedicalRecord medicalRecord)
			throws AlreadyExistsException {
		LOGGER.debug("at addMedicalRecord methode ");
		if (((medicalRecord.getFirstName() == null)
				|| (medicalRecord.getLastName() == null))
				|| (medicalRecord.getFirstName().isBlank())
				|| (medicalRecord.getLastName().isBlank())) {
			LOGGER.error("Invalid name  HttpStatus :{}", HttpStatus.NO_CONTENT);
			return ResponseEntity.badRequest().body("Invalid name");
		}
		medicalRecordService.addMedicalRecord(medicalRecord);
		LOGGER.info(
				"MedicalRecord of {} {} saved with success   HttpStatus :{}",
				medicalRecord.getFirstName(), medicalRecord.getLastName(),
				HttpStatus.CREATED);
		return new ResponseEntity<>(
				medicalRecordConverter.toMedicalRecordDTO(medicalRecord),
				HttpStatus.CREATED);

	}
	@PutMapping
	public ResponseEntity<?> updateMedicalRecord(
			@RequestBody MedicalRecord medicalRecord)
			throws MedicalRecordNotFoundException {
		LOGGER.debug("at updateMedicalRecord methode ");
		if (((medicalRecord.getFirstName() == null)
				|| (medicalRecord.getLastName() == null))
				|| (medicalRecord.getFirstName().isBlank())
				|| (medicalRecord.getLastName().isBlank())) {
			LOGGER.error("Invalid name  HttpStatus :{}", HttpStatus.NO_CONTENT);
			return ResponseEntity.badRequest().body("Invalid name");
		} else {
			medicalRecordService.updateMedicalRecord(medicalRecord);
			LOGGER.info(
					"MedicalRecord of {} {} Updated with success   HttpStatus :{}",
					medicalRecord.getFirstName(), medicalRecord.getLastName(),
					HttpStatus.OK);
			return new ResponseEntity<>(
					medicalRecordConverter.toMedicalRecordDTO(medicalRecord),
					HttpStatus.OK);
		}
	}
	@DeleteMapping
	public ResponseEntity<?> deleteMedicalRecord(@RequestParam String firstName,
			@RequestParam String lastName)
			throws MedicalRecordNotFoundException {
		LOGGER.debug("at deleteMedicalRecord methode ");
		if (((firstName == null) || (lastName == null)) || (firstName.isBlank())
				|| (lastName.isBlank())) {
			LOGGER.error("Invalid name  HttpStatus :{}",
					HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body("Invalid name");
		} else {
			MedicalRecordDTO medicalRecord = medicalRecordService
					.deleteMedicalRecord(firstName, lastName);
			LOGGER.info(
					"MedicalRecord of {} {} deleted with success   HttpStatus :{}",
					medicalRecord.getFirstName(), medicalRecord.getLastName(),
					HttpStatus.OK);
			return new ResponseEntity<>(medicalRecord, HttpStatus.OK);
		}
	}
}
