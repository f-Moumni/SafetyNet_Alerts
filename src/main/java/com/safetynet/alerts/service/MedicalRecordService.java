package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.DTO.MedicalRecordDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.util.MedicalRecordConverter;
@Service
public class MedicalRecordService implements IMedicalRecordService {

	@Autowired
	MedicalRecordRepository medicalRecordRepository;
	@Autowired
	MedicalRecordConverter medicalRecordConverter;

	@Override
	public List<MedicalRecordDTO> findAll() throws DataNotFoundException {
		List<MedicalRecord> medicaleRecords = medicalRecordRepository.findAll();
		if (!medicaleRecords.isEmpty()) {
			return medicalRecordConverter
					.toMedicalRecordDTOList(medicaleRecords);
		} else
			throw new DataNotFoundException("Medical records Data not found");

	}

	@Override
	public void addMedicalRecord(MedicalRecord medicalRecordToAdd)
			throws AlreadyExistsException {
		MedicalRecord medicalRecord = medicalRecordRepository.findByName(
				medicalRecordToAdd.getFirstName(),
				medicalRecordToAdd.getLastName());
		if (medicalRecord != null) {
			throw new AlreadyExistsException(medicalRecordToAdd.getFirstName()
					+ "  " + medicalRecordToAdd.getLastName()
					+ " already has a medical record");
		} else {
			medicalRecordRepository.addMedicalRecord(medicalRecordToAdd);

		}

	}

	@Override
	public MedicalRecordDTO updateMedicalRecord(
			MedicalRecord medicalRecordToUpdate)
			throws MedicalRecordNotFoundException {
		MedicalRecord medicalRecord = medicalRecordRepository.findByName(
				medicalRecordToUpdate.getFirstName(),
				medicalRecordToUpdate.getLastName());
		if (medicalRecord != null) {
			return medicalRecordConverter
					.toMedicalRecordDTO(medicalRecordRepository
							.updateMedicalRecord(medicalRecordToUpdate));
		} else {
			throw new MedicalRecordNotFoundException(
					"the name cannot be changed");
		}
	}

	@Override
	public MedicalRecordDTO deleteMedicalRecord(String firstName,
			String lastName) throws MedicalRecordNotFoundException {
		MedicalRecord medicalRecord = findByName(firstName, lastName);
		medicalRecordRepository.deleteMedicalRecord(medicalRecord);
		return medicalRecordConverter.toMedicalRecordDTO(medicalRecord);

	}
	@Override
	public MedicalRecord findByName(String firstName, String lastName)
			throws MedicalRecordNotFoundException {
		MedicalRecord medicalRecord = medicalRecordRepository
				.findByName(firstName, lastName);
		if (medicalRecord != null) {
			return medicalRecord;
		} else {
			throw new MedicalRecordNotFoundException(
					"person " + firstName + " " + lastName + " not found");
		}

	}

}
