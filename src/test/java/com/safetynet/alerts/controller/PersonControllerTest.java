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
import com.safetynet.alerts.DTO.PersonDTO;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;
import com.safetynet.alerts.util.PersonConverter;
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	IPersonService personService;
	@MockBean
	PersonConverter personConverter;

	private static List<PersonDTO> personsDTO = new ArrayList<>();
	static {
		personsDTO.add(new PersonDTO("John", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-6512", "jaboyd@email.com"));
		personsDTO.add(new PersonDTO("Jacob", "Boyd", "1509 Culver st",
				"Culver", 97451, "841-874-6513", "drk@email.com"));
		personsDTO.add(new PersonDTO("Tessa", "Carman", "834 Binoc Ave",
				"Culver", 97451, "841-874-6512", "tenz@email.com"));
		personsDTO.add(new PersonDTO("Sophia", "Zemicks", "892 Downing Ct",
				"Culver", 97451, "841-874-7878", "soph@email.com"));
	}

	@Test
	@Tag("getPersons")
	@DisplayName("getPersons test should return all persons in list and status OK")
	void getAllPersons_returnAllPersons_OK() throws Exception {
		// arrange
		when(personService.findAll()).thenReturn(personsDTO);
		// Act
		mvc.perform(
				get("/person/persons").contentType(MediaType.APPLICATION_JSON))// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(asJsonString(personsDTO)));
	}
	@Test
	@Tag("getPersons")
	@DisplayName("getPersons test with exception should return status NOT_FOUND")
	void getAllPersons_withDataNotFoundException_NotFound() throws Exception {
		// arrange
		when(personService.findAll()).thenThrow(DataNotFoundException.class);
		// act
		mvc.perform(
				get("/person/persons").contentType(MediaType.APPLICATION_JSON))// assert
				.andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	@Tag("PersonByName")
	@DisplayName("getPersonByName test should return person with given name and status Ok ")
	void getPersonByName_returnPersonWithName_OK() throws Exception {
		// arrange
		when(personService.findByName(anyString(), anyString()))
				.thenReturn(personsDTO.get(0));
		// act
		mvc.perform(get("/person").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "john").param("lastName", "Boyd"))// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(asJsonString(personsDTO.get(0))));
		verify(personService).findByName(anyString(), anyString());
	}
	@Test
	@Tag("PersonByName")
	@DisplayName("getPersonByName test with invalide name should return status BadRequest ")
	void getPersonByName_withInvalidName_badRequest() throws Exception {
		mvc.perform(get("/person").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "").param("lastName", ""))// assert
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));

	}
	@Test
	@Tag("PersonByName")
	@DisplayName("getPersonByName test with invalide Last name should return status BadRequest ")
	void getPersonByName_withInvalidLastName_badRequest() throws Exception {
		mvc.perform(get("/person").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "John").param("lastName", ""))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));

	}
	@Test
	@Tag("PersonByName")
	@DisplayName("getPersonByName test with an unregistered person should return status NOT_FOUND ")
	void getPersonByName_withAnUnregisteredPerson_NOT_FOUND() throws Exception {
		when(personService.findByName(anyString(), anyString()))
				.thenThrow(PersonNotFoundException.class);
		mvc.perform(get("/person").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "Lola").param("lastName", "laod"))
				.andDo(print()).andExpect(status().isNotFound());

	}
	@Test
	@Tag("PersonByName")
	@DisplayName("getPersonByName test with null name should return status BadRequest ")
	void PersonByName_with_nullName_returnBAD_REQUEST() throws Exception {
		mvc.perform(get("/person").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());
	}
	@Test
	@Tag("PersonByName")
	@DisplayName("getPersonByName test with null last name should return status BadRequest ")
	void PersonByName_with_nullLastName_returnBAD_REQUEST() throws Exception {
		mvc.perform(get("/person").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "John")).andDo(print())
				.andExpect(status().isBadRequest());
	}
	@Test
	@Tag("deletePerson")
	@DisplayName("deletePerson test should return status OK")
	void deletePerson_returnPersonDeleted_OK() throws Exception {
		when(personService.deletePerson(anyString(), anyString()))
				.thenReturn(personsDTO.get(0));
		mvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "John").param("lastName", "Boyd"))
				.andDo(print()).andExpect(status().isOk());
		verify(personService).deletePerson(anyString(), anyString());
	}
	@Test
	@Tag("deletePerson")
	@DisplayName("deletePerson test with invalid name should return status BAD_REQUEST")
	void deletePerson_with_invalidName_returnPersonDeleted_BAD_REQUEST()
			throws Exception {
		mvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "").param("lastName", "")).andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));
	}
	@Test
	@Tag("deletePerson")
	@DisplayName("deletePerson test with null name should return status BAD_REQUEST")
	void deletePerson_with_nullName_returnPersonDeleted_BAD_REQUEST()
			throws Exception {
		mvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());

	}
	@Test
	@Tag("deletePerson")
	@DisplayName("deletePerson test with invalid last name should return status BAD_REQUEST")
	void deletePerson_with_nulllastName_returnPersonDeleted_BAD_REQUEST()
			throws Exception {
		mvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "john").param("lastName", ""))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));

	}
	@Test
	@Tag("deletePerson")
	@DisplayName("deletePerson test with an unregistered person should return status NOT_FOUND ")
	void deletePerson_withAnUnregisteredPerson_NOT_FOUND() throws Exception {
		when(personService.deletePerson(anyString(), anyString()))
				.thenThrow(PersonNotFoundException.class);
		mvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "Lola").param("lastName", "laod"))
				.andDo(print()).andExpect(status().isNotFound());
		verify(personService).deletePerson(anyString(), anyString());

	}
	@Test
	@Tag("addPerson")
	@DisplayName("addPerson test should return status CREATED")
	void addPerson_returnPersonSaved_CREATED() throws Exception {
		PersonDTO persondto = new PersonDTO("John", "Boyd", "1509 Culver st",
				"Culver", 97451, "841-874-6512", "jaboyd@email.com");
		Person person = new Person("John", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		when(personConverter.toPerson(persondto)).thenReturn(person);
		when(personService.addPerson(person)).thenReturn(persondto);
		mvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(persondto))).andDo(print())
				.andExpect(status().isCreated());
		verify(personConverter).toPerson(any(PersonDTO.class));
	}
	@Test
	@Tag("addPerson")
	@DisplayName("addPerson test with invalde name should return status BAD_REQUEST")
	void addPerson_WithInvalidName_BAD_REQUEST() throws Exception {
		PersonDTO persondto = new PersonDTO("", "", "1509 Culver st", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		mvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(persondto))).andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));
	}
	@Test
	@Tag("addPerson")
	@DisplayName("addPerson test with invalde Last name should return status BAD_REQUEST")
	void addPerson_WithInvalidLastName_BAD_REQUEST() throws Exception {
		PersonDTO persondto = new PersonDTO("lola", " ", "1509 Culver st",
				"Culver", 97451, "841-874-6512", "jaboyd@email.com");
		mvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(persondto))).andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));
	}
	@Test
	@Tag("updatePerson")
	@DisplayName("updatePerson test with invalde Last name should return status BAD_REQUEST")
	void updatePerson_WithInvalidLastName_BAD_REQUEST() throws Exception {
		PersonDTO persondto = new PersonDTO("lola", " ", "1509 Culver st",
				"Culver", 97451, "841-874-6512", "jaboyd@email.com");
		mvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(persondto))).andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));
	}
	@Test
	@Tag("updatePerson")
	@DisplayName("updatePerson test with invalde name should return status BAD_REQUEST")
	void updatePerson_WithInvalidName_BAD_REQUEST() throws Exception {
		PersonDTO persondto = new PersonDTO("", " ", "1509 Culver st", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		mvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(persondto))).andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Invalid name")));
	}
	@Test
	@Tag("updatePerson")
	@DisplayName("updatePerson test should return status OK")
	void updatePerson_WithValidName_OK() throws Exception {
		PersonDTO persondto = new PersonDTO("John", "Boyd", "1509 Culver st",
				"Culver", 97451, "841-874-6512", "jaboyd@email.com");
		Person person = new Person("John", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		when(personService.updatePerson(person)).thenReturn(persondto);
		when(personConverter.toPerson(persondto)).thenReturn(person);
		mvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(persondto))).andDo(print())
				.andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
