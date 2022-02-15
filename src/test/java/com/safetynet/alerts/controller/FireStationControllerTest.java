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
import com.safetynet.alerts.DTO.FireStationDTO;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.IFireStationService;
import com.safetynet.alerts.util.FireStationConverter;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	IFireStationService fireStationService;
	@MockBean
	FireStationConverter fireStationConverter;
	private static List<FireStationDTO> fireStationsDTO = new ArrayList<>();
	static {
		fireStationsDTO.add(new FireStationDTO("1509 Culver St", 3));
		fireStationsDTO.add(new FireStationDTO("29 15th St", 2));
		fireStationsDTO.add(new FireStationDTO("834 Binoc Ave", 3));

	}
	@Test
	@Tag("getFireStations")
	@DisplayName("getFireStations test return a list of fireStation and status OK")
	void getFireStations_returnAllFireStations_OK() throws Exception {

		when(fireStationService.findAll()).thenReturn(fireStationsDTO);
		mvc.perform(
				get("/firestations").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(asJsonString(fireStationsDTO)));
	}
	@Test
	@Tag("getFireStations")
	@DisplayName("getFireStations test with exception should return status NOT_FOUND")
	void getFireStations_withDataNotFoundException_NotFound() throws Exception {
		when(fireStationService.findAll())
				.thenThrow(DataNotFoundException.class);
		mvc.perform(
				get("/firestations").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound());
	}
	@Test
	@Tag("addFireStation")
	@DisplayName("addFireStation test should return status CREATED")
	void addFireStation_returnFireStationSaved_CREATED() throws Exception {
		FireStationDTO fireStationdto = fireStationsDTO.get(0);
		FireStation fireStation = new FireStation("1509 Culver St", 3);
		when(fireStationConverter.toFireStation(fireStationdto))
				.thenReturn(fireStation);
		when(fireStationService.addFireStation(fireStation))
				.thenReturn(fireStationdto);
		mvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(fireStationdto))).andDo(print())
				.andExpect(status().isCreated());
		verify(fireStationConverter).toFireStation(any(FireStationDTO.class));
	}
	@Test
	@Tag("addFireStation")
	@DisplayName("addFireStation test with invalde station number should return status BAD_REQUEST")
	void addFireStation_WithInvalidStationNumber_BAD_REQUEST()
			throws Exception {
		FireStationDTO fireStationdto = new FireStationDTO("1509 Culver St",
				-1);
		mvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(fireStationdto))).andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString(
						"Invalid fire station number or address")));
	}
	@Test
	@Tag("addFireStation")
	@DisplayName("addFireStation test with invalde address should return status BAD_REQUEST")
	void addFireStation_WithInvalidAddress_BAD_REQUEST() throws Exception {
		FireStationDTO fireStationdto = new FireStationDTO(" ", 3);
		mvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(fireStationdto))).andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString(
						"Invalid fire station number or address")));
	}
	@Test
	@Tag("updateFireStation")
	@DisplayName("updateFireStation test with invalde station number should return status BAD_REQUEST")
	void updateFireStation_WithInvalidStationNumber_BAD_REQUEST()
			throws Exception {
		FireStationDTO fireStationdto = new FireStationDTO("1509 Culver St",
				-1);
		mvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(fireStationdto))).andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString(
						"Invalid fire station number or address")));
	}
	@Test
	@Tag("updateFireStation")
	@DisplayName("updateFireStation test with invalde address should return status BAD_REQUEST")
	void updateFireStation_WithInvalidAddress_BAD_REQUEST() throws Exception {
		FireStationDTO fireStationdto = new FireStationDTO(" ", 2);
		mvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(fireStationdto))).andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString(
						"Invalid fire station number or address")));
	}

	@Test
	@Tag("updateFireStation")
	@DisplayName("updateFireStation test should return status OK")
	void updateFireStation_OK() throws Exception {
		FireStationDTO fireStationdto = new FireStationDTO("125 rue nationale ",
				2);
		FireStation fireStation = new FireStation("125 rue nationale ", 2);
		when(fireStationService.updateFireStation(fireStation))
				.thenReturn(fireStationdto);
		when(fireStationConverter.toFireStation(fireStationdto))
				.thenReturn(fireStation);
		mvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(fireStationdto))).andDo(print())
				.andExpect(status().isOk());
	}
	@Test
	@Tag("deleteFireStation")
	@DisplayName("deleteFireStation test with invalid address should return status BAD_REQUEST")
	void deleteFireStation_withInvalid_BAD_REQUEST() throws Exception {

		mvc.perform(delete("/firestation")
				.contentType(MediaType.APPLICATION_JSON).param("address", " "))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid address")));
	}
	@Test
	@Tag("deleteFireStation")
	@DisplayName("deleteFireStation test should return status OK")
	void deleteFireStation_OK() throws Exception {
		when(fireStationService.deleteFireStation(anyString()))
				.thenReturn(fireStationsDTO.get(0));
		mvc.perform(
				delete("/firestation").contentType(MediaType.APPLICATION_JSON)
						.param("address", "1509 Culver St"))
				.andDo(print()).andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
