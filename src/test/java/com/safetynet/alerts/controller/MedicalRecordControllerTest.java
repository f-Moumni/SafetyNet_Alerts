package com.safetynet.alerts.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.DTO.MedicalRecordDTO;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;
import com.safetynet.alerts.util.MedicalRecordConverter;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalRecordControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	IMedicalRecordService medicalRecordService;
	@MockBean
	MedicalRecordConverter medicalRecordConverter;
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
	}
	@Test
	@Tag("getmedicalRecords")
	@DisplayName("getmedicalRecords test should return all medicalRecords in list and status OK")
	void getAllmedicalRecords_returnAllmedicalRecords_OK() throws Exception {
		// arrange
		when(medicalRecordService.findAll()).thenReturn(medicalRecordsDTO);
		// act
		mvc.perform(
				get("/medicalRecord").contentType(MediaType.APPLICATION_JSON))
				// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(asJsonString(medicalRecordsDTO)));
	}
	@Test
	@Tag("getMedicalRecords")
	@DisplayName("getMedicalRecords test with exception should return status NOT_FOUND")
	void getAllMedicalRecords_withDataNotFoundException_NotFound()
			throws Exception {
		// arrange
		when(medicalRecordService.findAll())
				.thenThrow(DataNotFoundException.class);
		// act
		mvc.perform(
				get("/medicalRecord").contentType(MediaType.APPLICATION_JSON))
				// assert
				.andDo(print()).andExpect(status().isNotFound());
	}
	@Test
	@Tag("addMedicalRecord")
	@DisplayName("addMedicalRecord test should return status CREATED")
	void addMedicalRecord_returnMedicalRecordSaved_CREATED() throws Exception {
		// arrange
		MedicalRecordDTO medicalRecorddto = medicalRecordsDTO.get(0);
		MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd",
				"03/06/1984",
				new ArrayList<String>(
						List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan")));
		when(medicalRecordConverter.toMedicalRecord(medicalRecorddto))
				.thenReturn(medicalRecord);
		when(medicalRecordService.addMedicalRecord(medicalRecord))
				.thenReturn(medicalRecorddto);
		// act
		mvc.perform(
				post("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(asJsonString(medicalRecorddto)))
				// assert
				.andDo(print()).andExpect(status().isCreated())
				.andExpect(content().string(asJsonString(medicalRecorddto)));
		verify(medicalRecordConverter)
				.toMedicalRecord(any(MedicalRecordDTO.class));
	}
	@Test
	@Tag("addMedicalRecord")
	@DisplayName("addMedicalRecord test with invalde name should return status BAD_REQUEST")
	void addMedicalRecord_WithInvalidName_BAD_REQUEST() throws Exception {
		// arrange
		MedicalRecordDTO medicalRecorddto = new MedicalRecordDTO("", " ",
				"03/06/1984",
				new ArrayList<String>(
						List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan")));
		// act
		mvc.perform(
				post("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(asJsonString(medicalRecorddto)))
				// assert
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));
	}
	@Test
	@Tag("addMedicalRecord")
	@DisplayName("addMedicalRecord test with invalde Last name should return status BAD_REQUEST")
	void addMedicalRecord_WithInvalidLastName_BAD_REQUEST() throws Exception {
		// arrange
		MedicalRecordDTO medicalRecorddto = new MedicalRecordDTO("john", " ",
				"03/06/1984", null, null);
		// act
		mvc.perform(
				post("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(asJsonString(medicalRecorddto)))
				// assert
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));
	}
	@Test
	@Tag("updateMedicalRecord")
	@DisplayName("updateMedicalRecord test should return status OK")
	void updateMedicalRecord_WithValidName_OK() throws Exception {
		// arrange
		MedicalRecordDTO medicalRecorddto = medicalRecordsDTO.get(0);
		MedicalRecord medicalRecord = new MedicalRecord("lola", "laod",
				"03/06/1984", null, null);
		when(medicalRecordService.updateMedicalRecord(medicalRecord))
				.thenReturn(medicalRecorddto);
		when(medicalRecordConverter.toMedicalRecord(medicalRecorddto))
				.thenReturn(medicalRecord);
		// act
		mvc.perform(
				put("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(asJsonString(medicalRecorddto)))
				// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(asJsonString(medicalRecorddto)));
	}

	@Test
	@Tag("updateMedicalRecord")
	@DisplayName("updateMedicalRecord test with invalde name should return status BAD_REQUEST")
	void updateMedicalRecord_WithInvalidName_BAD_REQUEST() throws Exception {
		// arrange
		MedicalRecordDTO medicalRecorddto = new MedicalRecordDTO("", " ",
				"03/06/1984",
				new ArrayList<String>(
						List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan")));
		// act
		mvc.perform(
				put("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(asJsonString(medicalRecorddto)))
				// assert
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));
	}
	@Test
	@Tag("updateMedicalRecord")
	@DisplayName("updateMedicalRecord test with invalde Last name should return status BAD_REQUEST")
	void updateMedicalRecord_WithInvalidLastName_BAD_REQUEST()
			throws Exception {
		// arrange
		MedicalRecordDTO medicalRecorddto = new MedicalRecordDTO("lola", " ",
				"03/06/1984", null, null);
		// act
		mvc.perform(
				put("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(asJsonString(medicalRecorddto)))
				// assert
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));
	}
	@Test
	@Tag("deleteMedicalRecord")
	@DisplayName("deleteMedicalRecord test should return status OK")
	void deleteMedicalRecord_returnMedicalRecordDeleted_OK() throws Exception {
		// arrange
		when(medicalRecordService.deleteMedicalRecord(anyString(), anyString()))
				.thenReturn(medicalRecordsDTO.get(0));
		// act
		mvc.perform(
				delete("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.param("firstName", "John").param("lastName", "Boyd"))
				// assert
				.andDo(print()).andExpect(status().isOk()).andExpect(content()
						.string(asJsonString(medicalRecordsDTO.get(0))));
	}
	@Test
	@Tag("deleteMedicalRecord")
	@DisplayName("deleteMedicalRecord test with invalid name should return status BAD_REQUEST")
	void deleteMedicalRecord_with_invalidName_returnMedicalRecordDeleted_BAD_REQUEST()
			throws Exception {
		// act
		mvc.perform(
				delete("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.param("firstName", "").param("lastName", ""))
				// ASSERT
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));
	}
	@Test
	@Tag("deleteMedicalRecord")
	@DisplayName("deleteMedicalRecord test with null name should return status BAD_REQUEST")
	void deleteMedicalRecord_with_nullName_returnMedicalRecordDeleted_BAD_REQUEST()
			throws Exception {
		// act
		mvc.perform(delete("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				// ASSERT
				.andExpect(status().isBadRequest());

	}
	@Test
	@Tag("deleteMedicalRecord")
	@DisplayName("deleteMedicalRecord test with invalid last name should return status BAD_REQUEST")
	void deleteMedicalRecord_with_nulllastName_returnMedicalRecordDeleted_BAD_REQUEST()
			throws Exception {
		// act
		mvc.perform(
				delete("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.param("firstName", "john").param("lastName", ""))// ASSERT
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));

	}
	@Test
	@Tag("deleteMedicalRecord")
	@DisplayName("deleteMedicalRecord test with an unregistered medicalRecord should return status NOT_FOUND ")
	void deleteMedicalRecord_withAnUnregisteredMedicalRecord_NOT_FOUND()
			throws Exception {
		// arrange
		when(medicalRecordService.deleteMedicalRecord(anyString(), anyString()))
				.thenThrow(MedicalRecordNotFoundException.class);
		// act
		mvc.perform(
				delete("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.param("firstName", "Lola").param("lastName", "laod"))
				// assert
				.andDo(print()).andExpect(status().isNotFound());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
