package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		return medicalRecords.stream()
				.filter(medicalRecord -> medicalRecord.getLastName()
						.equalsIgnoreCase(lastName))
				.filter(medicalRecord -> medicalRecord.getFirstName()
						.equalsIgnoreCase(firstName))
				.collect(Collectors.toList()).get(0);
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
