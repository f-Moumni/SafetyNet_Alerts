package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.DTO.MedicalRecordDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.model.MedicalRecord;
/**
 * IMedicalRecordService interface for business processing of medicalRecord CRUD
 * operations
 * 
 * @author fatima
 *
 */
public interface IMedicalRecordService {
	/**
	 * to get all medicalRecords in data
	 * 
	 * @return list of medicalRecord
	 * @throws DataNotFoundException
	 */
	public List<MedicalRecordDTO> findAll() throws DataNotFoundException;

	/**
	 * addMedicalRecord save a given medicalRecord
	 * 
	 * @param medicalRecord
	 *            to save
	 * @return medicalRecord saved
	 * @throws AlreadyExistsException
	 */
	public MedicalRecordDTO addMedicalRecord(MedicalRecord medicalRecord)
			throws AlreadyExistsException;

	/**
	 * updateMedicalRecord update MedicalRecord
	 * 
	 * @param medicalRecord
	 *            to update
	 * @return MedicalRecord updated
	 * @throws MedicalRecordNotFoundException
	 */
	public MedicalRecordDTO updateMedicalRecord(MedicalRecord medicalRecord)
			throws MedicalRecordNotFoundException;
	/**
	 * deleteMedicalRecord delete medical record of person
	 * 
	 * @param person's
	 *            firstName
	 * @param person's
	 *            lastName
	 * @return medical record deleted
	 * @throws MedicalRecordNotFoundException
	 */
	public MedicalRecordDTO deleteMedicalRecord(String firstName,
			String lastName) throws MedicalRecordNotFoundException;

	/**
	 * findByName get MedicalRecord of a given person name
	 * 
	 * @param person's
	 *            firstName
	 * @param person's
	 *            lastName
	 * @return person's MedicalRecord
	 * @throws MedicalRecordNotFoundException
	 */
	public MedicalRecord findByName(String firstName, String lastName)
			throws MedicalRecordNotFoundException;

}
