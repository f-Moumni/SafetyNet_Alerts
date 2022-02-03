package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordRepository {

	void addMedicalRecord(MedicalRecord medicalRecord);

	List<MedicalRecord> findAll();

	void deleteMedicalRecord(MedicalRecord medicalRecord);

	MedicalRecord updateMedicalRecord(MedicalRecord medicalRecordUpdated);

}
