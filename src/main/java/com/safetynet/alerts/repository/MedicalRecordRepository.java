package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository {

	private List<MedicalRecord> medicalRecords = new ArrayList<>();

	@Autowired
	public MedicalRecordRepository(List<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
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
		return medicalRecords.stream()
				.filter(medicalRecord -> medicalRecord.getLastName()
						.equalsIgnoreCase(lastName))
				.filter(medicalRecord -> medicalRecord.getFirstName()
						.equalsIgnoreCase(firstName))
				.findFirst().orElse(null);
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
		if (medicalRecord != null) {
			deleteMedicalRecord(medicalRecord);
			addMedicalRecord(medicalRecordUpdated);
			return medicalRecordUpdated;
		}
		return medicalRecord;

	}
}
