package com.safetynet.alerts.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.service.IPersonService;
//@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	@MockBean
	IPersonService personService;

	@Test
	void testGetPersons() throws Exception {
		// mockMvc.perform(get("/person/persons")).andExpect(status().isOk());
	}

}
