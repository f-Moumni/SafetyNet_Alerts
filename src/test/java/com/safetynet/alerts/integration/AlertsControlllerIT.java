package com.safetynet.alerts.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.safetynet.alerts.data.ReadDataFromJson;

@SpringBootTest(properties = "data.jsonFilePath=src/test/resources/data.json")
@AutoConfigureMockMvc
public class AlertsControlllerIT {
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
	@Tag("Alertsfirestation")
	@DisplayName("getCoveredPopulationByStation test should return all people who are covered by a given station and status OK")
	void getCoveredPopulationByStatio_returnAllPersonsCouvred_OK()
			throws Exception {
		// Act
		mvc.perform(get("/firestation").contentType(MediaType.APPLICATION_JSON)
				.param("station", "3"))// assert
				.andDo(print()).andExpectAll(status().isOk(),
						jsonPath("$.personsCouverd[0].firstName").value("John"),
						jsonPath("$.personsCouverd[0].lastName").value("Boyd"),
						jsonPath("$.numberOfAdults").value(10),
						jsonPath("$.numberOfChildren").value(3));
	}
	@Test
	@Tag("Alertsfirestation")
	@DisplayName("getCoveredPopulationByStation test with non person couverd should return  status OK")
	void getCoveredPopulationByStatio_withNopersonCouvred_returnAllPersonsCouvred_OK()
			throws Exception {
		// Act
		mvc.perform(get("/firestation").contentType(MediaType.APPLICATION_JSON)
				.param("station", "5"))// assert
				.andDo(print()).andExpectAll(status().isOk(),
						jsonPath("$.numberOfAdults").value(0),
						jsonPath("$.numberOfChildren").value(0));

	}
	@Test
	@Tag("getChildenByaddress")
	@DisplayName("getChildenByaddress test should return all children and other household members for a given address and status OK")
	void getChildenByaddress_returnChildrensAndHousehold_OK() throws Exception {
		// Act
		mvc.perform(get("/childAlert").contentType(MediaType.APPLICATION_JSON)
				.param("address", "1509 Culver st"))// assert
				.andDo(print()).andExpectAll(status().isOk(),
						jsonPath("$.[0].firstName").value("Tenley"),
						jsonPath("$.[0].lastName").value("Boyd"),
						jsonPath("$.[0].age").value(10),
						jsonPath("$.[0].family[0]").value("John Boyd"));

	}
	@Test
	@Tag("getChildenByaddress")
	@DisplayName("getChildenByaddress test with no child in the  address should return empty list and status OK")
	void getChildenByaddress_withNochil_returnEmptylist_OK() throws Exception {
		// Act
		mvc.perform(get("/childAlert").contentType(MediaType.APPLICATION_JSON)
				.param("address", "951 LoneTow Rd"))// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$").isEmpty())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}
	@Test
	@Tag("getPhoneNumberByStation")
	@DisplayName("getPhoneNumberByStation should return all the phone numbers of the inhabitants covered by a given station and status OK")
	void getPhoneNumberByStation_returnPhoneNumbers_OK() throws Exception {
		// Act
		mvc.perform(get("/phoneAlert").contentType(MediaType.APPLICATION_JSON)
				.param("station", "3"))// assert
				.andDo(print()).andExpectAll(status().isOk(),
						jsonPath("$.[0]").value("841-874-6741"));

	}
	@Test
	@Tag("getInhabitantsByAddress")
	@DisplayName("getInhabitantsByAddress should return all inhabitants of an address and the station that covers this given address and status OK")
	void getInhabitantsByAddress_OK() throws Exception {
		// Act
		mvc.perform(get("/fire").contentType(MediaType.APPLICATION_JSON)
				.param("address", "1509 Culver st"))// assert
				.andDo(print()).andExpectAll(status().isOk(),
						jsonPath("$.station").value(3),
						jsonPath("$.address").value("1509 Culver st"),
						jsonPath("$.inhabitants[0].firstName").value("John"),
						jsonPath("$.inhabitants[0].lastName").value("Boyd"));
	}
	@Test
	@Tag("getHomesByStation")
	@DisplayName("getHomesByStation test should return addresses and inhabitants covered by a given station with status OK")
	void getHomesByStation_returnOK() throws Exception {
		// Act
		mvc.perform(get("/flood").contentType(MediaType.APPLICATION_JSON)
				.param("station", "3"))// assert
				.andDo(print()).andExpectAll(status().isOk(),
						jsonPath("$[0].address").value("1509 Culver St"),
						jsonPath("$[0].inhabitants[0].firstName").value("John"),
						jsonPath("$[0].inhabitants[0].lastName").value("Boyd"));
	}
	@Test
	@Tag("getPersonInfosByName")
	@DisplayName("getPersonInfosByName test should return the information of a given person and the people who have the same name with status OK")
	void getPersonInfosByName_returnOK() throws Exception {
		// Act
		mvc.perform(get("/personInfo").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "john").param("lastName", "Boyd"))// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpectAll(status().isOk(), jsonPath("$[0].age").value(37),
						jsonPath("$[0].firstName").value("John"),
						jsonPath("$[0].lastName").value("Boyd"),
						jsonPath("$[1].age").value(32),
						jsonPath("$[1].firstName").value("Jacob"),
						jsonPath("$[1].lastName").value("Boyd"));
	}
	@Test
	@Tag("getCommunityEmails")
	@DisplayName("getCommunityEmails should return email addresses of a community status OK")
	void getCommunityEmails_returnOK() throws Exception {
		// Act
		mvc.perform(
				get("/CommunityEmail").contentType(MediaType.APPLICATION_JSON)
						.param("city", "Culver"))// assert
				.andDo(print()).andExpectAll(status().isOk(),
						jsonPath("$[0]").value("drk@email.com"),
						jsonPath("$[1]").value("soph@email.com"),
						jsonPath("$[2]").value("reg@email.com"));
	}
}
