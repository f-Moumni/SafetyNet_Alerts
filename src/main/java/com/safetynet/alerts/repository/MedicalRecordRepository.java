package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.data.ReadDataFromJson;
import com.safetynet.alerts.model.MedicalRecord;
/**
 * MedicalRecordRepository class allows to store and manage MedicalRecord data
 * 
 * @see IMedicalRecordRepository
 * @see ReadDataFromJson
 * @author Fatima
 *
 */
@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository {

	/**
	 * Create a SLF4J/LOG4J LOGGER instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MedicalRecordRepository.class);

	/**
	 * MedicalRecord data list
	 */
	private List<MedicalRecord> medicalRecords = new ArrayList<>();

	/**
	 * a class Constructor
	 * 
	 * @param medicalRecords
	 */
	@Autowired
	public MedicalRecordRepository(List<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}

	/**
	 * findAll to get all medicalRecords in data
	 * 
	 * @return list of medicalRecords
	 */
	@Override
	public List<MedicalRecord> findAll() {
		LOGGER.debug("getting all medicalRecords in data base  ");
		return medicalRecords;
	}

	/**
	 * addMedicalRecord to save a given medicalRecord
	 * 
	 * @param medicalRecord
	 *            to save
	 */
	@Override
	public void addMedicalRecord(MedicalRecord medicalRecord) {
		LOGGER.debug("saving MedicalRecord {}", medicalRecord);
		medicalRecords.add(medicalRecord);
	}

	/**
	 * get medical record of given person's name
	 * 
	 * @param person's
	 *            firstName
	 * @param person's
	 *            lastName
	 * @return person's MedicalRecord
	 */
	@Override
	public MedicalRecord findByName(String firstName, String lastName) {
		LOGGER.debug("search for MedicalRecord of {} {}", firstName, lastName);
		return medicalRecords.stream()
				.filter(medicalRecord -> medicalRecord.getLastName()
						.equalsIgnoreCase(lastName))
				.filter(medicalRecord -> medicalRecord.getFirstName()
						.equalsIgnoreCase(firstName))
				.findFirst().orElse(null);
	}
	/**
	 * delete a given medicalRecord from data
	 * 
	 * @param medicalRecord
	 *            to delete
	 */
	@Override
	public void deleteMedicalRecord(MedicalRecord medicalRecord) {
		LOGGER.debug("deleting MedicalRecord {}", medicalRecord);
		medicalRecords.remove(medicalRecord);
	}

	/**
	 * update a medical recored in data
	 * 
	 * @param medicalRecord
	 *            to up date
	 * @return medicalRecord updated
	 */
	@Override
	public MedicalRecord updateMedicalRecord(
			MedicalRecord medicalRecordToUpdated) {
		LOGGER.debug("updating MedicalRecord {}", medicalRecordToUpdated);
		MedicalRecord medicalRecord = findByName(
				medicalRecordToUpdated.getFirstName(),
				medicalRecordToUpdated.getLastName());
		if (medicalRecord != null) {
			deleteMedicalRecord(medicalRecord);
			addMedicalRecord(medicalRecordToUpdated);
			return medicalRecordToUpdated;
		}
		return medicalRecord;

	}
}
