package com.safetynet.alerts.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.DTO.MedicalRecordDTO;
import com.safetynet.alerts.data.ReadDataFromJson;
import com.safetynet.alerts.util.JsonAlertMapper;

@SpringBootTest(properties = "data.jsonFilePath=src/test/resources/data.json")
@AutoConfigureMockMvc
public class MedicalRecordControllerIT {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private ReadDataFromJson readData;

	@BeforeEach
	void SetUp() throws IOException {

		readData.readData();
	}
	@AfterEach
	public void cleanup() throws IOException {
		readData.clearData();
	}
	@Test
	@Tag("getMedicalRecords")
	@DisplayName("getMedicalRecords test should return all medicalRecords in list and status OK")
	void getAllMedicalRecords_returnAllmedicalRecords_OK() throws Exception {

		// act
		mvc.perform(
				get("/medicalRecord").contentType(MediaType.APPLICATION_JSON))
				// assert
				.andDo(print()).andExpectAll(status().isOk(),
						jsonPath("$[0].firstName").value("John"),
						jsonPath("$[0].lastName").value("Boyd"),
						jsonPath("$[0].birthdate").value("03/06/1984"));
	}
	@Test
	@Tag("addMedicalRecord")
	@DisplayName("addMedicalRecord test with unregistered medical record should return status CREATED")
	void addMedicalRecord_withUnregisteredMedicalRecord_returnCreated()
			throws Exception {
		// arrange
		MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO("Eric", "Boyd",
				"03/06/1984",
				new ArrayList<String>(
						List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan")));

		// act
		mvc.perform(
				post("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(
								JsonAlertMapper.asJsonString(medicalRecordDTO)))
				// assert
				.andDo(print()).andExpect(status().isCreated())
				.andExpect(content().string(
						JsonAlertMapper.asJsonString(medicalRecordDTO)));
	}
	@Test
	@Tag("addMedicalRecord")
	@DisplayName("addMedicalRecord test with registered medical record should return status AlreadyReported ")
	void addMedicalRecord_withRegisteredMedcialRecord_returnAlreadyReported()
			throws Exception {
		// arrange
		MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO("John", "Boyd",
				"03/06/1984",
				new ArrayList<String>(
						List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan")));

		// act
		mvc.perform(
				post("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(
								JsonAlertMapper.asJsonString(medicalRecordDTO)))
				// assert
				.andDo(print()).andExpectAll(status().isAlreadyReported(),
						jsonPath("$.message").value(
								"John  Boyd already has a medical record"));
	}
	@Test
	@Tag("updateMedicalRecord")
	@DisplayName("updateMedicalRecord test registered medical record should return status OK")
	void updateMedicalRecord_WithRegisteredMedicalRecord_returnOK()
			throws Exception {
		// arrange
		MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO("Jacob",
				"Boyd", "03/06/1984", null, null);
		// act
		mvc.perform(
				put("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(
								JsonAlertMapper.asJsonString(medicalRecordDTO)))
				// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(
						JsonAlertMapper.asJsonString(medicalRecordDTO)));
	}
	@Test
	@Tag("updateMedicalRecord")
	@DisplayName("updateMedicalRecord test with unregistered medical record should return status NotFound")
	void updateMedicalRecord_WithUnregisteredMedicalRecord_returnNotFound()
			throws Exception {
		// arrange
		MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO("lola", "Boyd",
				"03/06/1984", null, null);
		// act
		mvc.perform(
				put("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(
								JsonAlertMapper.asJsonString(medicalRecordDTO)))
				// assert
				.andDo(print())
				.andExpectAll(status().isNotFound(), jsonPath("$.message")
						.value("the name cannot be changed"));
	}
	@Test
	@Tag("deleteMedicalRecord")
	@DisplayName("deleteMedicalRecord test registered medical record should return status OK")
	void deleteMedicalRecord_WithregisteredMedicalRecord_returnMedicalRecordDeleted_OK()
			throws Exception {
		// arrange
		MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO("Eric",
				"Cadigan", "08/06/1945",
				new ArrayList<String>(List.of("tradoxidine:400mg")),
				new ArrayList<String>());
		// act
		mvc.perform(delete("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "Eric").param("lastName", "Cadigan"))
				// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(
						JsonAlertMapper.asJsonString(medicalRecordDTO)));
	}
	@Test
	@Tag("deleteMedicalRecord")
	@DisplayName("deleteMedicalRecord test with unregistered medical record should iureturn status NotFound")
	void deleteMedicalRecord_WithUnregisteredMedicalRecord_returnNotFound()
			throws Exception {
		// act
		mvc.perform(delete("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "lola").param("lastName", "Cadigan"))
				// assert
				.andDo(print())
				.andExpectAll(status().isNotFound(), jsonPath("$.message")
						.value("person lola Cadigan do not have medical records"));
	}

}
