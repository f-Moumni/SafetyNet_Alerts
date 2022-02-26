package com.safetynet.alerts.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.safetynet.alerts.DTO.MedicalRecordDTO;
import com.safetynet.alerts.model.MedicalRecord;
/**
 * MedicalRecordConverter a MedicalRecord mapper
 * 
 * @author Fatima
 *
 */
@Component
public class MedicalRecordConverter {
	/**
	 * Create a SLF4J/LOG4J LOGGER instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MedicalRecordConverter.class);

	/**
	 * toMedicalRecordDTO map a given MedicalRecord to MedicalRecordDTO Object
	 * 
	 * @param MedicalRecord
	 * @return MedicalRecordDTO
	 */
	public MedicalRecordDTO toMedicalRecordDTO(MedicalRecord medicalRecord) {
		LOGGER.debug("mapping to MedicalRecordDTO for MedicalRecord {}",
				medicalRecord);
		return new MedicalRecordDTO(medicalRecord.getFirstName(),
				medicalRecord.getLastName(), medicalRecord.getBirthdate(),
				medicalRecord.getMedications(), medicalRecord.getAllergies());
	}

	/**
	 * toMedicalRecord map a given MedicalRecordDTO to MedicalRecord Object
	 * 
	 * @param medicalRecordDTO
	 * @return MedicalRecord
	 */
	public MedicalRecord toMedicalRecord(MedicalRecordDTO medicalRecord) {
		LOGGER.debug("mapping to MedicalRecord for MedicalRecordDTO {}",
				medicalRecord);
		return new MedicalRecord(medicalRecord.getFirstName(),
				medicalRecord.getLastName(), medicalRecord.getBirthdate(),
				medicalRecord.getMedications(), medicalRecord.getAllergies());
	}

	/**
	 * toMedicalRecordDTOList map a given MedicalRecord objects list to
	 * MedicalRecordDTO Object list
	 * 
	 * @param list
	 *            of MedicalRecord
	 * @return list of MedicalRecordDTO
	 */
	public List<MedicalRecordDTO> toMedicalRecordDTOList(
			List<MedicalRecord> medicalRecords) {
		LOGGER.debug(
				"mapping to list of MedicalRecordDTO for MedicalRecords {}",
				medicalRecords);
		List<MedicalRecordDTO> medicalRecordsDTO = new ArrayList<MedicalRecordDTO>();
		medicalRecords.forEach(medicalRecord -> medicalRecordsDTO
				.add(toMedicalRecordDTO(medicalRecord)));
		return medicalRecordsDTO;

	}
}
