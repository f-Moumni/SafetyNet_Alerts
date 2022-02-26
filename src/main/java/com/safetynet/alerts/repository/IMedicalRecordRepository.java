package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;
/**
 * IMedicalRecordRepository interface allows to manage medicalRecord data
 * 
 * @author fatima
 *
 */
public interface IMedicalRecordRepository {
	/**
	 * addMedicalRecord to save a given medicalRecord
	 * 
	 * @param medicalRecord
	 *            to save
	 */
	public void addMedicalRecord(MedicalRecord medicalRecord);

	/**
	 * findAll to get all fire medicalRecords in data
	 * 
	 * @return list of medicalRecord
	 */
	public List<MedicalRecord> findAll();

	/**
	 * delete a given medicalRecord from data
	 * 
	 * @param medicalRecord
	 *            to delete
	 */
	public void deleteMedicalRecord(MedicalRecord medicalRecord);

	/**
	 * update a medical recored in data
	 * 
	 * @param medicalRecord
	 *            to up date
	 * @return medicalRecord updated
	 */
	public MedicalRecord updateMedicalRecord(
			MedicalRecord medicalRecordtoUpdate);

	/**
	 * get medical record of given person's name
	 * 
	 * @param person's
	 *            firstName
	 * @param person's
	 *            lastName
	 * @return person's MedicalRecord
	 */
	public MedicalRecord findByName(String firstName, String lastName);

}
