package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.DTO.MedicalRecordDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordService {

	List<MedicalRecordDTO> findAll() throws DataNotFoundException;

	MedicalRecordDTO addMedicalRecord(MedicalRecord medicalRecord)
			throws AlreadyExistsException;

	MedicalRecordDTO updateMedicalRecord(MedicalRecord medicalRecord)
			throws MedicalRecordNotFoundException;

	MedicalRecordDTO deleteMedicalRecord(String firstName, String lastName)
			throws MedicalRecordNotFoundException;

	MedicalRecord findByName(String firstName, String lastName)
			throws MedicalRecordNotFoundException;

}
