package com.safetynet.alerts.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.safetynet.alerts.DTO.MedicalRecordDTO;
import com.safetynet.alerts.model.MedicalRecord;
@Component
public class MedicalRecordConverter {

	public MedicalRecordDTO toMedicalRecordDTO(MedicalRecord medicalRecord) {
		return new MedicalRecordDTO(medicalRecord.getFirstName(),
				medicalRecord.getLastName(), medicalRecord.getBirthdate(),
				medicalRecord.getMedications(), medicalRecord.getAllergies());
	}
	public MedicalRecord toMedicalRecord(MedicalRecordDTO medicalRecord) {
		return new MedicalRecord(medicalRecord.getFirstName(),
				medicalRecord.getLastName(), medicalRecord.getBirthdate(),
				medicalRecord.getMedications(), medicalRecord.getAllergies());
	}

	public List<MedicalRecordDTO> toMedicalRecordDTOList(
			List<MedicalRecord> medicalRecords) {
		List<MedicalRecordDTO> medicalRecordsDTO = new ArrayList<MedicalRecordDTO>();
		medicalRecords.forEach(medicalRecord -> medicalRecordsDTO
				.add(toMedicalRecordDTO(medicalRecord)));
		return medicalRecordsDTO;

	}
}
