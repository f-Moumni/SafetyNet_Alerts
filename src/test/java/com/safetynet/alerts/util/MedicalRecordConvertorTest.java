package com.safetynet.alerts.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.safetynet.alerts.DTO.MedicalRecordDTO;
import com.safetynet.alerts.model.MedicalRecord;

class MedicalRecordConvertorTest {
	private static MedicalRecordConverter mediRecordConverter;

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

	private static List<MedicalRecordDTO> medicalRecordsDTO = new ArrayList<>();
	static {

		medicalRecordsDTO.add(new MedicalRecordDTO("John", "Boyd", "03/06/1984",
				new ArrayList<String>(
						List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan"))));
		medicalRecordsDTO
				.add(new MedicalRecordDTO("Jacob", "Boyd", "03/06/1989",
						new ArrayList<String>(List.of("pharmacol:5000mg",
								"terazine:10mg", "noznazol:250mg")),
						new ArrayList<String>()));
		medicalRecordsDTO
				.add(new MedicalRecordDTO("Eric", "Cadigan", "08/06/1945",
						new ArrayList<String>(List.of("tradoxidine:400mg")),
						new ArrayList<String>()));
		medicalRecordsDTO.add(new MedicalRecordDTO("Clive", "Ferguson",
				"03/06/1994",
				new ArrayList<String>(
						List.of("ibupurin:200mg", "hydrapermazol:400mg")),
				new ArrayList<String>(List.of("nillacilan"))));
	}
	@BeforeEach
	void setUp() throws Exception {
		mediRecordConverter = new MedicalRecordConverter();
	}
	@Test
	@Tag("toMedicalRecordDTO")
	@DisplayName("toMedicalRecordDTO test should return MedicalRecordDTO of given MedicalRecord")
	void toMedicalRecordDTO_Test() {
		// arrange
		MedicalRecord medicalRecord = medicalRecords.get(0);
		// act
		MedicalRecordDTO result = mediRecordConverter
				.toMedicalRecordDTO(medicalRecord);
		// assert
		assertThat(result).usingRecursiveComparison()
				.isEqualTo(medicalRecordsDTO.get(0));
	}
	@Test
	@Tag("toMedicalRecord")
	@DisplayName("toMedicalRecord test should return MedicalRecord of given MedicalRecordDTO")
	void toMedicalRecord_Test() {
		// arrange
		MedicalRecordDTO medicalRecordDTO = medicalRecordsDTO.get(0);
		// act
		MedicalRecord result = mediRecordConverter
				.toMedicalRecord(medicalRecordDTO);
		// assert
		assertThat(result).usingRecursiveComparison()
				.isEqualTo(medicalRecords.get(0));
	}

	@Test
	@Tag("toMedicalRecordDTOList")
	@DisplayName("toMedicalRecordDTOList test should return list of MedicalRecordDTO of given list MedicalRecord")
	void toMedicalRecordDTOlist_Test() {
		// act
		List<MedicalRecordDTO> result = mediRecordConverter
				.toMedicalRecordDTOList(medicalRecords);
		// assert
		assertThat(result).usingRecursiveComparison()
				.isEqualTo(medicalRecordsDTO);
	}

}
