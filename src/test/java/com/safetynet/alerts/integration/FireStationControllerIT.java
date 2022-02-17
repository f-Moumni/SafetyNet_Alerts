package com.safetynet.alerts.integration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.FireStation;
@SpringBootTest
@AutoConfigureMockMvc
class FireStationControllerIT {

	@Autowired
	private MockMvc mvc;

	@Test
	@Tag("getFireStations")
	@DisplayName("getFireStations test return a list of fireStation and status OK")
	void getFireStations_test() throws Exception {
		// act
		mvc.perform(get("/firestations")).andExpect(status().isOk())// assert
				.andExpect(jsonPath("$[1].address", is("29 15th St")));
	}
	@Test
	@Tag("addFireStation")
	@DisplayName("addFireStation test with unregistered fire station should return status CREATED")
	void addFireStation_withUnregisteredFierStation_returnCreated()
			throws Exception {
		// arrange
		FireStation fs = new FireStation("1509 National St", 3);
		// act
		mvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(fs)))
				.andDo(print()).andExpect(status().isCreated())
				.andExpect(content().string(asJsonString(fs)));

	}
	@Test
	@Tag("addFireStation")
	@DisplayName("addFireStation test with fire station already registered should return status AlreadyReported")
	void addFireStation_registeredFireStation_retrunAlreadyReported()
			throws Exception {
		// arrange
		FireStation fs = new FireStation("1509 Culver St", 3);
		// act
		mvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(fs)))
				.andDo(print()).andExpect(status().isAlreadyReported())
				.andExpect(content().string(containsString(
						"this FireStation 3 at the address 1509 Culver St is already exsits")));

	}
	@Test
	@Tag("updateFireStation")
	@DisplayName("updateFireStation test with registered fire station should return status OK")
	void updateFireStation_registeredFireStation_retrunOK() throws Exception {
		// arrange
		FireStation fs = new FireStation("834 Binoc Ave", 2);
		// act
		mvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(fs)))// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(asJsonString(fs)));

	}
	@Test
	@Tag("updateFireStation")
	@DisplayName("updateFireStation test with unregistered fire station should return status Not_found")
	void updateFireStation_unregisteredFireStation_retrunNot_found()
			throws Exception {
		// arrange
		FireStation fs = new FireStation("1509 national St", 2);
		// act
		mvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(fs)))// assert
				.andDo(print()).andExpect(status().isNotFound())
				.andExpect(content().string(containsString(
						"the address  :1509 national St cannot be changed")));

	}
	@Test
	@Tag("deleteFireStation")
	@DisplayName("deleteFireStation test with unregistered fire station should return status Not_found")
	void deleteFireStation_unregisteredFireStation_retrunNot_found()
			throws Exception {
		// act
		mvc.perform(
				delete("/firestation").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.param("address", "1509 national St"))// assert
				.andDo(print()).andExpect(status().isNotFound())
				.andExpect(content().string(containsString(
						"firestation at address  :1509 national St not found")));

	}
	@Test
	@Tag("deleteFireStation")
	@DisplayName("deleteFireStation test with registered fire station should return status ok")
	void deleteFireStation_unregisteredFireStation_retrunOK() throws Exception {
		// arrange
		FireStation fs = new FireStation("489 Manchester St", 4);
		// act
		mvc.perform(
				delete("/firestation").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.param("address", "489 Manchester St"))// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(asJsonString(fs)));

	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
