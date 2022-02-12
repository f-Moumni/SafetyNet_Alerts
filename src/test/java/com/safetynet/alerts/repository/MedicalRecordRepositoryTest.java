package com.safetynet.alerts.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.safetynet.alerts.model.MedicalRecord;

class MedicalRecordRepositoryTest {

	private static IMedicalRecordRepository medicalRecordRepository;
	private static List<MedicalRecord> medicalRecords = new ArrayList<>();
	static {

		medicalRecords.add(new MedicalRecord("John", "Boyd", "03/06/1984",
				new ArrayList<String>(
						List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan"))));
		medicalRecords.add(new MedicalRecord("Jacob", "Boyd", "03/06/1989",
				new ArrayList<String>(List.of("pharmacol:5000mg",
						"terazine:10mg", "noznazol:250mg")),
				new ArrayList<String>()));
		medicalRecords.add(new MedicalRecord("Eric", "Cadigan", "08/06/1945",
				new ArrayList<String>(List.of("tradoxidine:400mg")),
				new ArrayList<String>()));
		medicalRecords.add(new MedicalRecord("Clive", "Ferguson", "03/06/1994",
				new ArrayList<String>(
						List.of("ibupurin:200mg", "hydrapermazol:400mg")),
				new ArrayList<String>(List.of("nillacilan"))));
	}
	@BeforeEach
	void setUp() throws Exception {
		medicalRecordRepository = new MedicalRecordRepository(medicalRecords);
	}
	@Test
	@Tag("findAll")
	@DisplayName("find all test should return a list of medicalRecord")

	void findAll_test() {
		// act
		List<MedicalRecord> result = medicalRecordRepository.findAll();
		// assert
		assertThat(result).isEqualTo(medicalRecords);
	}
	@Test
	@Tag("findByName")
	@DisplayName("find by name test should return medical record for a given name")
	void findByName_test() {
		// arrange
		MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd",
				"03/06/1984",
				new ArrayList<String>(
						List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan")));
		// act
		MedicalRecord result = medicalRecordRepository.findByName(
				medicalRecord.getFirstName(), medicalRecord.getLastName());
		// assert
		assertThat(result).isEqualTo(medicalRecord);

	}
	@Test
	@Tag("save")
	@DisplayName("add medical record test should save medical record in the list")
	void addMedicalRecord_test() {
		// arrange
		MedicalRecord medicalRecord = new MedicalRecord("Sarah", "Boyd",
				"03/06/1988",
				new ArrayList<String>(
						List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan")));
		// act
		medicalRecordRepository.addMedicalRecord(medicalRecord);
		// assert
		assertThat(medicalRecords).contains(medicalRecord);

	}

	@Test
	@Tag("delete")
	@DisplayName("delete medical record test should remove medical record for given name frome the list")
	void deleteMedicalRecord_test() {
		// arrange
		MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd",
				"03/06/1984",
				new ArrayList<String>(
						List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan")));
		// act
		medicalRecordRepository.deleteMedicalRecord(medicalRecord);
		// assert
		assertThat(medicalRecords).doesNotContain(medicalRecord);

	}
	@Test
	@Tag("update")
	@DisplayName("update medical record test should update medical record for given name and return e medical record updated")
	void updateMedicalRecord_test() {
		// arrange
		MedicalRecord medicalRecord = new MedicalRecord("Clive", "Ferguson",
				"03/06/1994", new ArrayList<String>(),
				new ArrayList<String>(List.of("nillacilan")));
		// act
		MedicalRecord result = medicalRecordRepository
				.updateMedicalRecord(medicalRecord);
		// assert
		assertThat(result.getAllergies())
				.isEqualTo(medicalRecord.getAllergies());
		assertThat(result.getMedications()).isEmpty();

	}

}
