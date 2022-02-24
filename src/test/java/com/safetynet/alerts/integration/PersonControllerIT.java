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

import com.safetynet.alerts.DTO.PersonDTO;
import com.safetynet.alerts.data.ReadDataFromJson;
import com.safetynet.alerts.util.JsonAlertMapper;

@SpringBootTest(properties = "data.jsonFilePath=src/test/resources/data.json")
@AutoConfigureMockMvc
public class PersonControllerIT {
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
	@Tag("getPersons")
	@DisplayName("getPersons test should return all persons in list and status OK")
	void getAllPersons_returnAllPersons_OK() throws Exception {
		// act
		mvc.perform(
				get("/person/persons").contentType(MediaType.APPLICATION_JSON))// assert
				.andDo(print()).andExpectAll(status().isOk(),
						jsonPath("$[0].firstName").value("John"),
						jsonPath("$[0].lastName").value("Boyd"),
						jsonPath("$[0].address").value("1509 Culver St"));
	}
	@Test
	@Tag("PersonByName")
	@DisplayName("getPersonByName test should return person with given name and status Ok ")
	void getPersonByName_returnPersonWithName_OK() throws Exception {
		// act
		mvc.perform(get("/person").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "jacob").param("lastName", "Boyd"))// assert
				.andDo(print()).andExpectAll(status().isOk(),
						jsonPath("$.firstName").value("Jacob"),
						jsonPath("$.lastName").value("Boyd"),
						jsonPath("$.address").value("1509 Culver St"));
	}
	@Test
	@Tag("PersonByName")
	@DisplayName("getPersonByName test with unregistred person should return Person not found status Not_found ")
	void getPersonByName_withUnregistredPerson_returnNotFound()
			throws Exception {
		// act
		mvc.perform(get("/person").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "lola").param("lastName", "red"))// assert
				.andDo(print())
				.andExpectAll(status().isNotFound(), jsonPath("$.message")
						.value("person lola red not found"));
	}
	@Test
	@Tag("deletePerson")
	@DisplayName("deletePerson test should return status OK")
	void deletePerson_returnPersonDeleted_OK() throws Exception {
		// act
		mvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "Jacob").param("lastName", "Boyd"))// assert
				.andDo(print()).andExpectAll(status().isOk(),
						jsonPath("$.firstName").value("Jacob"),
						jsonPath("$.lastName").value("Boyd"),
						jsonPath("$.address").value("1509 Culver St"));

	}
	@Test
	@Tag("deletePerson")
	@DisplayName("deletePerson test with unregistred person should return status Not_found")
	void deletePerson__withUnregistredPerson_returnPeronNotFound()
			throws Exception {
		// act
		mvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "robert").param("lastName", "Boyd"))// assert
				.andDo(print())
				.andExpectAll(status().isNotFound(), jsonPath("$.message")
						.value("person robert Boyd not found"));

	}
	@Test
	@Tag("addPerson")
	@DisplayName("addPerson test should return status CREATED")
	void addPerson_returnPersonSaved_CREATED() throws Exception {
		// arrange
		PersonDTO persondto = new PersonDTO("thomas", "Boyd", "1509 Culver st",
				"Culver", 97451, "841-874-6512", "jaboyd@email.com");
		// act
		mvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(JsonAlertMapper.asJsonString(persondto)))// assert
				.andDo(print()).andExpectAll(status().isCreated(), content()
						.string(JsonAlertMapper.asJsonString(persondto)));

	}
	@Test
	@Tag("addPerson")
	@DisplayName("addPerson test with person already exsits should return status AlreadyReported")
	void addPerson__withregistredPerson_returnPeronAlreadyReported()
			throws Exception {
		// arrange
		PersonDTO persondto = new PersonDTO("John", "Boyd", "1509 Culver st",
				"Culver", 97451, "841-874-6512", "jaboyd@email.com");
		// act
		mvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(JsonAlertMapper.asJsonString(persondto)))// assert
				.andDo(print()).andExpectAll(status().isAlreadyReported(),
						jsonPath("$.message")
								.value("this person John Boyd already exists"));

	}
	@Test
	@Tag("updatePerson")
	@DisplayName("updatePerson test should return status OK")
	void updatePerson_WithValidName_OK() throws Exception {
		// arrange
		PersonDTO persondto = new PersonDTO("Roger", "Boyd", "1509 Culver st",
				"Culver", 97451, "841-874-6512", "jaboyd@email.com");
		// act
		mvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(JsonAlertMapper.asJsonString(persondto)))// assert
				.andDo(print()).andExpectAll(status().isOk(), content()
						.string(JsonAlertMapper.asJsonString(persondto)));
	}
	@Test
	@Tag("updatePerson")
	@DisplayName("updatePerson test with unregistred person should return status Not found")
	void updatePerson__withUnregistredPerson_returnNotFound() throws Exception {
		// arrange
		PersonDTO persondto = new PersonDTO("lola", "Boyd", "1509 Culver st",
				"Culver", 97451, "841-874-6512", "jaboyd@email.com");
		// act
		mvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(JsonAlertMapper.asJsonString(persondto)))// assert
				.andDo(print())
				.andExpectAll(status().isNotFound(), jsonPath("$.message")
						.value("the name cannot be changed"));

	}

}
