package com.safetynet.alerts.controller;

import java.util.List;

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
import com.safetynet.alerts.exceptions.BadRequestException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;
import com.safetynet.alerts.util.MedicalRecordConverter;

import lombok.Data;
@Data
@RestController
@RequestMapping("medicalRecord")
public class MedicalRecordController {
	private final IMedicalRecordService medicalRecordService;

	@Autowired
	public MedicalRecordController(
			final IMedicalRecordService medicalRecordService) {
		this.medicalRecordService = medicalRecordService;
	}
	@Autowired
	MedicalRecordConverter medicalRecordConverter;

	@GetMapping
	public ResponseEntity<List<MedicalRecordDTO>> getPersons()
			throws DataNotFoundException {
		return new ResponseEntity<>(medicalRecordService.findAll(),
				HttpStatus.OK);

	}
	@PostMapping
	public ResponseEntity<MedicalRecordDTO> addMedicalRecord(
			@RequestBody MedicalRecord medicalRecord)
			throws AlreadyExistsException, BadRequestException {

		if (((medicalRecord.getFirstName() == null)
				|| (medicalRecord.getLastName() == null))
				|| (medicalRecord.getFirstName().isBlank())
				|| (medicalRecord.getLastName().isBlank())) {
			throw new BadRequestException(
					"the first and the last name are required ");
		} else {
			medicalRecordService.addMedicalRecord(medicalRecord);

			return new ResponseEntity<>(
					medicalRecordConverter.toMedicalRecordDTO(medicalRecord),
					HttpStatus.CREATED);

		}

	}
	@PutMapping
	public ResponseEntity<MedicalRecordDTO> updateMedicalRecord(
			@RequestBody MedicalRecord medicalRecord)
			throws BadRequestException, MedicalRecordNotFoundException {

		if (((medicalRecord.getFirstName() == null)
				|| (medicalRecord.getLastName() == null))
				|| (medicalRecord.getFirstName().isBlank())
				|| (medicalRecord.getLastName().isBlank())) {
			throw new BadRequestException(
					"the first and the last name are required ");
		} else {
			medicalRecordService.updateMedicalRecord(medicalRecord);
			return new ResponseEntity<>(
					medicalRecordConverter.toMedicalRecordDTO(medicalRecord),
					HttpStatus.OK);
		}
	}
	@DeleteMapping
	public ResponseEntity<MedicalRecordDTO> deleteMedicalRecord(
			@RequestParam String firstName, @RequestParam String lastName)
			throws MedicalRecordNotFoundException, BadRequestException {
		if (((firstName == null) || (lastName == null)) || (firstName.isBlank())
				|| (lastName.isBlank())) {
			throw new BadRequestException(
					"firstName and lastName are required");
		} else {
			MedicalRecordDTO medicalRecord = medicalRecordService
					.deleteMedicalRecord(firstName, lastName);
			return new ResponseEntity<>(medicalRecord, HttpStatus.OK);
		}
	}
}
