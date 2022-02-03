package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordService {

	List<MedicalRecord> findAll() throws DataNotFoundException;

	void addMedicalRecord(MedicalRecord medicalRecord)
			throws AlreadyExistsException;

	MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord)
			throws MedicalRecordNotFoundException;

	MedicalRecord deleteMedicalRecord(String firstName, String lastName)
			throws MedicalRecordNotFoundException;

}
