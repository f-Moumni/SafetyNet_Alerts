package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.MedicalRecord;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Data
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
}
