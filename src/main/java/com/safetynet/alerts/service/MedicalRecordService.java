package com.safetynet.alerts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.DTO.MedicalRecordDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.IMedicalRecordRepository;
import com.safetynet.alerts.util.MedicalRecordConverter;

/**
 * MedicalRecordService class for business processing of medicalRecord CRUD
 * operations
 * 
 * @author fatima
 *
 */
@Service
public class MedicalRecordService implements IMedicalRecordService {

	/**
	 * Create a SLF4J/LOG4J LOGGER instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MedicalRecordService.class);

	/**
	 * IMedicalRecordRepository's implement class reference.
	 */
	@Autowired
	IMedicalRecordRepository medicalRecordRepository;

	/**
	 * MedicalRecordConverter MedicalRecord mapper
	 */
	@Autowired
	MedicalRecordConverter medicalRecordConverter;

	/**
	 * to get all medicalRecords in data
	 * 
	 * @return list of medicalRecord
	 * @throws DataNotFoundException
	 */
	@Override
	public List<MedicalRecordDTO> findAll() throws DataNotFoundException {
		List<MedicalRecord> medicaleRecords = medicalRecordRepository.findAll();
		LOGGER.debug(" find All Medical Records start");
		LOGGER.info(" getting all Medical Records ");
		if (medicaleRecords != null) {
			return medicalRecordConverter
					.toMedicalRecordDTOList(medicaleRecords);
		} else
			LOGGER.error("Medical records Data not found");
		throw new DataNotFoundException("Medical records Data not found");
	}

	/**
	 * addMedicalRecord save a given medicalRecord
	 * 
	 * @param medicalRecord
	 *            to save
	 * @return medicalRecord saved
	 * @throws AlreadyExistsException
	 */
	@Override
	public MedicalRecordDTO addMedicalRecord(MedicalRecord medicalRecordToAdd)
			throws AlreadyExistsException {
		LOGGER.debug("add medical Record stard");
		LOGGER.info("saving of medicalRecord {}", medicalRecordToAdd);
		MedicalRecord medicalRecord = medicalRecordRepository.findByName(
				medicalRecordToAdd.getFirstName(),
				medicalRecordToAdd.getLastName());
		if (medicalRecord != null) {
			LOGGER.error("{} {} already has a medical record",
					medicalRecordToAdd.getFirstName(),
					medicalRecordToAdd.getLastName());
			throw new AlreadyExistsException(medicalRecordToAdd.getFirstName()
					+ "  " + medicalRecordToAdd.getLastName()
					+ " already has a medical record");
		} else {
			medicalRecordRepository.addMedicalRecord(medicalRecordToAdd);
			return medicalRecordConverter
					.toMedicalRecordDTO(medicalRecordToAdd);
		}

	}

	/**
	 * updateMedicalRecord update MedicalRecord
	 * 
	 * @param medicalRecord
	 *            to update
	 * @return MedicalRecord updated
	 * @throws MedicalRecordNotFoundException
	 */
	@Override
	public MedicalRecordDTO updateMedicalRecord(
			MedicalRecord medicalRecordToUpdate)
			throws MedicalRecordNotFoundException {
		LOGGER.debug("updating medical Record stard");
		LOGGER.info("updating medicalRecord {}", medicalRecordToUpdate);
		MedicalRecord medicalRecord = medicalRecordRepository.findByName(
				medicalRecordToUpdate.getFirstName(),
				medicalRecordToUpdate.getLastName());
		if (medicalRecord != null) {
			return medicalRecordConverter
					.toMedicalRecordDTO(medicalRecordRepository
							.updateMedicalRecord(medicalRecordToUpdate));
		}
		LOGGER.error("the name cannot be changed");
		throw new MedicalRecordNotFoundException("the name cannot be changed");

	}

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
	@Override
	public MedicalRecordDTO deleteMedicalRecord(String firstName,
			String lastName) throws MedicalRecordNotFoundException {
		LOGGER.debug("deleting medical Record stard");
		LOGGER.info("deleting medicalRecord of {} {}", firstName, lastName);
		MedicalRecord medicalRecord = findByName(firstName, lastName);
		medicalRecordRepository.deleteMedicalRecord(medicalRecord);
		return medicalRecordConverter.toMedicalRecordDTO(medicalRecord);

	}

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
	@Override
	public MedicalRecord findByName(String firstName, String lastName)
			throws MedicalRecordNotFoundException {
		LOGGER.debug("find medical Record by name stard");
		LOGGER.info("find medicalRecord of {} {}", firstName, lastName);
		MedicalRecord medicalRecord = medicalRecordRepository
				.findByName(firstName, lastName);
		if (medicalRecord != null) {
			return medicalRecord;
		} else {
			LOGGER.error("person {} {} do not have medical records", firstName,
					lastName);
			throw new MedicalRecordNotFoundException("person " + firstName + " "
					+ lastName + " do not have medical records");
		}

	}

}
