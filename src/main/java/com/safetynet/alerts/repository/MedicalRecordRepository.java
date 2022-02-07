package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository {

	private List<MedicalRecord> medicalRecords = new ArrayList<>();
	@Override
	public List<MedicalRecord> findAll() {
		return medicalRecords;
	}
	@Override
	public void addMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecords.add(medicalRecord);

	}
	@Override
	public MedicalRecord findByName(String firstName, String lastName) {
		MedicalRecord medicalRecord = null;
		for (MedicalRecord medRecord : medicalRecords) {
			if ((medRecord.getFirstName().equalsIgnoreCase(firstName))
					&& (medRecord.getLastName().equalsIgnoreCase(lastName))) {
				medicalRecord = medRecord;
				break;
			}
		}
		return medicalRecord;
	}
	@Override
	public void deleteMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecords.remove(medicalRecord);
	}
	@Override
	public MedicalRecord updateMedicalRecord(
			MedicalRecord medicalRecordUpdated) {
		MedicalRecord medicalRecord = findByName(
				medicalRecordUpdated.getFirstName(),
				medicalRecordUpdated.getLastName());
		deleteMedicalRecord(medicalRecord);
		addMedicalRecord(medicalRecordUpdated);
		return medicalRecordUpdated;

	}
}
