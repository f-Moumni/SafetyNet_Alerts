package com.safetynet.alerts.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

		when(medicalRecordService.findAll()).thenReturn(medicalRecordsDTO);
		mvc.perform(
				get("/medicalRecord").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(asJsonString(medicalRecordsDTO)));
	}
	@Test
	@Tag("getMedicalRecords")
	@DisplayName("getMedicalRecords test with exception should return status NOT_FOUND")
	void getAllMedicalRecords_withDataNotFoundException_NotFound()
			throws Exception {

		when(medicalRecordService.findAll())
				.thenThrow(DataNotFoundException.class);
		mvc.perform(
				get("/medicalRecord").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound());
	}
	@Test
	@Tag("addMedicalRecord")
	@DisplayName("addMedicalRecord test should return status CREATED")
	void addMedicalRecord_returnMedicalRecordSaved_CREATED() throws Exception {
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
		mvc.perform(
				post("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(asJsonString(medicalRecorddto)))
				.andDo(print()).andExpect(status().isCreated());
		verify(medicalRecordConverter)
				.toMedicalRecord(any(MedicalRecordDTO.class));
	}
	@Test
	@Tag("addMedicalRecord")
	@DisplayName("addMedicalRecord test with invalde name should return status BAD_REQUEST")
	void addMedicalRecord_WithInvalidName_BAD_REQUEST() throws Exception {
		MedicalRecordDTO medicalRecorddto = new MedicalRecordDTO("", " ",
				"03/06/1984",
				new ArrayList<String>(
						List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan")));
		mvc.perform(
				post("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(asJsonString(medicalRecorddto)))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));
	}
	@Test
	@Tag("addMedicalRecord")
	@DisplayName("addMedicalRecord test with invalde Last name should return status BAD_REQUEST")
	void addMedicalRecord_WithInvalidLastName_BAD_REQUEST() throws Exception {
		MedicalRecordDTO medicalRecorddto = new MedicalRecordDTO("john", " ",
				"03/06/1984", null, null);
		mvc.perform(
				post("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(asJsonString(medicalRecorddto)))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
