package com.safetynet.alerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alerts.DTO.MedicalRecordDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.IMedicalRecordRepository;
import com.safetynet.alerts.util.MedicalRecordConverter;
@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceTest {

	@Mock
	private static IMedicalRecordRepository medicalRecordRepository;
	@Mock
	private static MedicalRecordConverter medicalRecordConverter;
	@InjectMocks
	private static MedicalRecordService medicalRecordService;
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
	@Test
	@Tag("findAll")
	@DisplayName("find all test should return a list of medicalRecord")
	void findAll_test_shouldReturnListOfMedicalRecord()
			throws DataNotFoundException {
		// arrange
		when(medicalRecordRepository.findAll()).thenReturn(medicalRecords);
		when(medicalRecordConverter.toMedicalRecordDTOList(medicalRecords))
				.thenReturn(medicalRecordsDTO);
		// act
		List<MedicalRecordDTO> result = medicalRecordService.findAll();
		// assert
		verify(medicalRecordRepository).findAll();
		assertThat(result).isEqualTo(medicalRecordsDTO);
	}
	@Test
	@Tag("findAll")
	@DisplayName("find all test with empty data should Throw DataNotFoundException")
	void findAll_test_withEmptyDate_shouldThrowDataNotFoundException()
			throws DataNotFoundException {
		// arrange

		when(medicalRecordRepository.findAll()).thenReturn(null);
		// assert
		assertThrows(DataNotFoundException.class,
				() -> medicalRecordService.findAll());
		verify(medicalRecordRepository).findAll();
	}
	@Test
	@Tag("save")
	@DisplayName("add medical record test should save medical record in the list")
	void addMedicalRecord_test_shouldReturnMedicalRecord()
			throws MedicalRecordNotFoundException, AlreadyExistsException {
		// arrange
		MedicalRecord medicalRecord = medicalRecords.get(0);
		MedicalRecordDTO medicalRecordDTO = medicalRecordsDTO.get(0);
		when(medicalRecordRepository.findByName(anyString(), anyString()))
				.thenReturn(null);
		when(medicalRecordConverter.toMedicalRecordDTO(medicalRecord))
				.thenReturn(medicalRecordDTO);
		// act
		MedicalRecordDTO result = medicalRecordService
				.addMedicalRecord(medicalRecord);
		// assert
		assertThat(result).isEqualTo(medicalRecordDTO);
		verify(medicalRecordRepository).findByName(anyString(), anyString());
		verify(medicalRecordRepository).addMedicalRecord(medicalRecord);
	}
	@Test
	@Tag("save")
	@DisplayName("add medical record for a person who already has a should throw AlreadyExistsException")
	void addMedicalRecord_test_shouldThrowAlreadyExistsException()
			throws MedicalRecordNotFoundException, AlreadyExistsException {
		// arrange
		MedicalRecord medicalRecord = medicalRecords.get(0);
		when(medicalRecordRepository.findByName(anyString(), anyString()))
				.thenReturn(medicalRecord);
		// assert
		assertThrows(AlreadyExistsException.class,
				() -> medicalRecordService.addMedicalRecord(medicalRecord));
		verify(medicalRecordRepository).findByName(anyString(), anyString());
	}
	@Test
	@Tag("Update")
	@DisplayName("update medical record test should update medical record for given name and return e medical record updated")
	void updateMedicalRecord_test_ShouldReturnMedicalRecordUpdated()
			throws MedicalRecordNotFoundException {
		// arrange
		MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd",
				"03/06/1984",
				new ArrayList<String>(List.of("aznol:350mg",
						"hydrapermazol:100mg", "noznazol:250mg")),
				new ArrayList<String>(List.of("nillacilan", "nillac")));
		MedicalRecord medicalRecordToUpdate = medicalRecords.get(0);
		MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO("John", "Boyd",
				"03/06/1984",
				new ArrayList<String>(List.of("aznol:350mg",
						"hydrapermazol:100mg", "noznazol:250mg")),
				new ArrayList<String>(List.of("nillacilan", "nillac")));
		when(medicalRecordRepository.findByName(anyString(), anyString()))
				.thenReturn(medicalRecordToUpdate);
		when(medicalRecordRepository.updateMedicalRecord(medicalRecord))
				.thenReturn(medicalRecord);
		when(medicalRecordConverter.toMedicalRecordDTO(medicalRecord))
				.thenReturn(medicalRecordDTO);
		// Act
		MedicalRecordDTO result = medicalRecordService
				.updateMedicalRecord(medicalRecord);
		assertThat(result).isEqualTo(medicalRecordDTO);
		verify(medicalRecordRepository).findByName(anyString(), anyString());
	}

	@Test
	@Tag("update")
	@DisplayName("update medicalRecord test with an unregistered person should Throw PersonNotFoundException")
	void updatePerson_test_WithAnUnregisteredPerson_shouldThrowPersonNotFoundException()
			throws MedicalRecordNotFoundException {
		// arrange
		MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd",
				"03/06/1984",
				new ArrayList<String>(List.of("aznol:350mg",
						"hydrapermazol:100mg", "noznazol:250mg")),
				new ArrayList<String>(List.of("nillacilan", "nillac")));
		when(medicalRecordRepository.findByName(anyString(), anyString()))
				.thenReturn(null);
		// assert
		assertThrows(MedicalRecordNotFoundException.class,
				() -> medicalRecordService.updateMedicalRecord(medicalRecord));
		verify(medicalRecordRepository).findByName(anyString(), anyString());

	}
	@Test
	@Tag("delete")
	@DisplayName("delete medicalRecord test should remove a medicalRecord from a list and return medicalRecord deleted")
	void deleteMedicalRecord_test_shouldRemoveMedicalRecord_ReturnMedicalRecordDeleted()
			throws MedicalRecordNotFoundException {
		// arrange
		MedicalRecord medicalRecord = medicalRecords.get(0);
		MedicalRecordDTO medicalRecordDTO = medicalRecordsDTO.get(0);
		when(medicalRecordRepository.findByName(anyString(), anyString()))
				.thenReturn(medicalRecord);
		when(medicalRecordConverter.toMedicalRecordDTO(medicalRecord))
				.thenReturn(medicalRecordDTO);
		// act
		MedicalRecordDTO result = medicalRecordService.deleteMedicalRecord(
				medicalRecord.getFirstName(), medicalRecord.getLastName());
		// assert
		assertThat(result).isEqualTo(medicalRecordDTO);
		verify(medicalRecordRepository).deleteMedicalRecord(medicalRecord);

	}
	@Test
	@Tag("delete")
	@DisplayName("delete medicalRecord test with an unregistered person should Throw MedicalRecordNotFoundException")
	void deleteMedicalRecord_test_WithAnUnregisteredPerson_shouldThrowMedicalRecordNotFoundException() {
		// arrange
		MedicalRecord medicalRecord = medicalRecords.get(0);
		when(medicalRecordRepository.findByName(anyString(), anyString()))
				.thenReturn(null);
		// assert
		assertThrows(MedicalRecordNotFoundException.class,
				() -> medicalRecordService.deleteMedicalRecord(
						medicalRecord.getFirstName(),
						medicalRecord.getLastName()));
	}
	@Test
	@Tag("findByName")
	@DisplayName("find by name test with an unregistered medicalRecord should Throw MedicalRecordNotFoundException")
	void findByName_test_WithAnUnregisteredMedicalRecord_shouldThrowMedicalRecordNotFoundException() {
		// arrange
		when(medicalRecordRepository.findByName(anyString(), anyString()))
				.thenReturn(null);
		// assert
		assertThrows(MedicalRecordNotFoundException.class,
				() -> medicalRecordService.findByName(anyString(),
						anyString()));
		verify(medicalRecordRepository).findByName(anyString(), anyString());

	}
	@Test
	@Tag("findByName")
	@DisplayName("find by name test should return a medicalRecord with the given name")
	void findByName_test_shouldReturnMedicalRecord()
			throws MedicalRecordNotFoundException {
		// arrange
		MedicalRecord medicalRecord = medicalRecords.get(0);
		when(medicalRecordRepository.findByName(anyString(), anyString()))
				.thenReturn(medicalRecord);
		// act
		MedicalRecord result = medicalRecordService.findByName(
				medicalRecord.getFirstName(), medicalRecord.getLastName());
		// assert
		verify(medicalRecordRepository).findByName(anyString(), anyString());
		assertThat(result).isEqualTo(medicalRecord);

	}
}
