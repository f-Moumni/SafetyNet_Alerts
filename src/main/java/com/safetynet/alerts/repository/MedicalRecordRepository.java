package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.data.IReadData;
import com.safetynet.alerts.model.MedicalRecord;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Data
@Repository
public class MedicalRecordRepository {

	private final IReadData readData;
	private List<MedicalRecord> medicalRecords = new ArrayList<>();

	MedicalRecordRepository(IReadData readData) {
		log.debug("getting MedicalRecords list in respository");
		this.readData = readData;
		medicalRecords.addAll(readData.getListmedicalRecordNode());
	}

	public List<MedicalRecord> findAll() {
		return medicalRecords;
	}
}
