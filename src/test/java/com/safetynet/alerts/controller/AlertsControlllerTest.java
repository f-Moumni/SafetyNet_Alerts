package com.safetynet.alerts.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;
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
import com.safetynet.alerts.DTO.ChildDTO;
import com.safetynet.alerts.DTO.CoveredPopulationDTO;
import com.safetynet.alerts.DTO.FireDTO;
import com.safetynet.alerts.DTO.HomeDTO;
import com.safetynet.alerts.DTO.InhabitantDTO;
import com.safetynet.alerts.DTO.PersonAlertDTO;
import com.safetynet.alerts.DTO.PersonInfosDTO;
import com.safetynet.alerts.service.IAlertsService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AlertsController.class)
public class AlertsControlllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	IAlertsService alertsService;
	@Test
	@Tag("firestation")
	@DisplayName("getCoveredPopulationByStation test should return all people who are covered by a given station and status OK")
	void getCoveredPopulationByStatio_returnAllPersonsCouvred_OK()
			throws Exception {
		// arrange
		CoveredPopulationDTO coveredP = new CoveredPopulationDTO(Arrays.asList(
				new PersonAlertDTO("John", "Boyd", "1509 Culver st",
						"841-874-6512"),
				new PersonAlertDTO("Jacob", "Boyd", "1509 Culver st",
						"841-874-6513")),
				2, 0);
		when(alertsService.getPopulationCovredByStation(any(Integer.class)))
				.thenReturn(coveredP);
		// Act
		mvc.perform(get("/firestation").contentType(MediaType.APPLICATION_JSON)
				.param("station", "3"))// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(asJsonString(coveredP)));
		verify(alertsService).getPopulationCovredByStation(any(Integer.class));
	}
	@Test
	@Tag("firestation")
	@DisplayName("getCoveredPopulationByStation test with invalid station number should return status BAD_REQUEST")
	void getCoveredPopulationByStatio_withIvalidStationNumber_BAD_REQUEST()
			throws Exception {
		// Act
		mvc.perform(get("/firestation").contentType(MediaType.APPLICATION_JSON)
				.param("station", "0"))// assert
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid Station number"));

	}
	@Test
	@Tag("getChildenByaddress")
	@DisplayName("getChildenByaddress test should return all children and other household members for a given address and status OK")
	void getChildenByaddress_returnChildrensAndHousehold_OK() throws Exception {
		// arrange
		List<ChildDTO> childrensInAddress = Arrays.asList(new ChildDTO("Tenley",
				"Boyd", 4, Arrays.asList("John Boyd", "Jacob Boyd")));
		when(alertsService.getChildrenByAddress(anyString()))
				.thenReturn(childrensInAddress);
		// Act
		mvc.perform(get("/childAlert").contentType(MediaType.APPLICATION_JSON)
				.param("address", "1509 Culver st"))// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(asJsonString(childrensInAddress)));
		verify(alertsService).getChildrenByAddress(anyString());
	}
	@Test
	@Tag("getChildenByaddress")
	@DisplayName("getChildenByaddress test with invald address should return status BAD_REQUEST")
	void getChildenByaddress_withInvalidAddress_returnBAD_REQUEST()
			throws Exception {
		// Act
		mvc.perform(get("/childAlert").contentType(MediaType.APPLICATION_JSON)
				.param("address", " "))// assert
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid address"));
	}
	@Test
	@Tag("getPhoneNumberByStation")
	@DisplayName("getPhoneNumberByStation should return all the phone numbers of the inhabitants covered by a given station and status OK")
	void getPhoneNumberByStation_returnPhoneNumbers_OK() throws Exception {
		// arrange
		HashSet<String> phoneNumbers = new HashSet<>(
				Arrays.asList("841-874-6512", "841-874-6513", "841-874-6544"));
		when(alertsService.getPhoneNumberByStation(any(Integer.class)))
				.thenReturn(phoneNumbers);
		// Act
		mvc.perform(get("/phoneAlert").contentType(MediaType.APPLICATION_JSON)
				.param("station", "3"))// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(asJsonString(phoneNumbers)));
		verify(alertsService).getPhoneNumberByStation(any(Integer.class));
	}
	@Test
	@Tag("getPhoneNumberByStation")
	@DisplayName("getPhoneNumberByStation with invalid station should return status BAD_REQUEST")
	void getPhoneNumberByStations_withInvalidStationNumber_returnBAD_REQUEST()
			throws Exception {
		// Act
		mvc.perform(get("/phoneAlert").contentType(MediaType.APPLICATION_JSON)
				.param("station", "-1"))// assert
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid Station number"));
	}
	@Test
	@Tag("getInhabitantsByAddress")
	@DisplayName("getInhabitantsByAddress with invalid address should return status BAD_REQUEST")
	void getInhabitantsByAddress_withInvalidStationNumber_returnBAD_REQUEST()
			throws Exception {
		// Act
		mvc.perform(get("/fire").contentType(MediaType.APPLICATION_JSON)
				.param("address", " "))// assert
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid address"));
	}
	@Test
	@Tag("getInhabitantsByAddress")
	@DisplayName("getInhabitantsByAddress should return all inhabitants of an address and the station that covers this given address and status OK")
	void getInhabitantsByAddress_OK() throws Exception {
		// arrange
		FireDTO inhabitantsInAddress = new FireDTO("1509 Culver st", 3,
				Arrays.asList(
						new InhabitantDTO("Jonh", "Boyd", 37, "841-874-6512",
								null, null),
						new InhabitantDTO("Jacob", "Boyd", 34, "841-874-6882",
								null,
								Arrays.asList("nillacilan", "shellfish"))));

		when(alertsService.getInhabitantByAddress(anyString()))
				.thenReturn(inhabitantsInAddress);
		// Act
		mvc.perform(get("/fire").contentType(MediaType.APPLICATION_JSON)
				.param("address", "1509 Culver st"))// assert
				.andDo(print()).andExpect(status().isOk()).andExpect(
						content().string(asJsonString(inhabitantsInAddress)));
		verify(alertsService).getInhabitantByAddress(anyString());
	}

	@Test
	@Tag("getHomesByStation")
	@DisplayName("getHomesByStation with invalid station should return status BAD_REQUEST")
	void getfloodsByStation_withInvalidStationNumber_returnBAD_REQUEST()
			throws Exception {
		// Act
		mvc.perform(get("/flood").contentType(MediaType.APPLICATION_JSON)
				.param("station", "-1"))// assert
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid station number"));
	}
	@Test
	@Tag("getHomesByStation")
	@DisplayName("getHomesByStation test should return addresses and inhabitants covered by a given station with status OK")
	void getHomesByStation_returnOK() throws Exception {
		// arrange
		List<HomeDTO> homesCouvred = Arrays.asList(
				new HomeDTO("1509 Culver St", Arrays.asList(
						new InhabitantDTO("Jonh", "Boyd", 37, "841-874-6512",
								null, null),
						new InhabitantDTO("Jacob", "Boyd", 34, "841-874-6882",
								null,
								Arrays.asList("nillacilan", "shellfish")))),
				new HomeDTO("748 Townings Dr", Arrays.asList(
						new InhabitantDTO("Clive", "Stelzer", 24,
								"841-874-6666", null, null),
						new InhabitantDTO("Eric", "Stelzer", 34, "841-874-8888",
								null,
								Arrays.asList("nillacilan", "shellfish")))));
		when(alertsService.getHomesByStation(any(Integer.class)))
				.thenReturn(homesCouvred);
		// Act
		mvc.perform(get("/flood").contentType(MediaType.APPLICATION_JSON)
				.param("station", "3"))// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(asJsonString(homesCouvred)));
		verify(alertsService).getHomesByStation(any(Integer.class));
	}
	@Test
	@Tag("getPersonInfosByName")
	@DisplayName("getPersonInfosByName test should return the information of a given person and the people who have the same name with status OK")
	void getPersonInfosByName_returnOK() throws Exception {
		// arrange
		List<PersonInfosDTO> personInfos = Arrays.asList(
				new PersonInfosDTO("John", "Boyd", 37, "jboyd@gmail.com", null,
						null),
				new PersonInfosDTO("Jacob", "Boyd", 34, "jacob@hotmail.com",
						null, Arrays.asList("nillacilan", "shellfish")));

		when(alertsService.getPersonInfosByName(anyString(), anyString()))
				.thenReturn(personInfos);
		// Act
		mvc.perform(get("/personInfo").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "john").param("lastName", "Boyd"))// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(asJsonString(personInfos)));
		verify(alertsService).getPersonInfosByName(anyString(), anyString());
	}
	@Test
	@Tag("getPersonInfosByName")
	@DisplayName("getPersonInfosByName with invalid name should return status BAD_REQUEST")
	void getPersonInfosByName_withInvalidName_returnBAD_REQUEST()
			throws Exception {
		// Act
		mvc.perform(get("/personInfo").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", " ").param("lastName", ""))// assert
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid name"));
	}
	@Test
	@Tag("getPersonInfosByName")
	@DisplayName("getPersonInfosByName with invalid last name should return status BAD_REQUEST")
	void getPersonInfosByName_withInvalidLastName_returnBAD_REQUEST()
			throws Exception {
		// Act
		mvc.perform(get("/personInfo").contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "John").param("lastName", ""))// assert
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid name"));
	}
	@Test
	@Tag("getCommunityEmails")
	@DisplayName("getCommunityEmails with invalid City should return status BAD_REQUEST")
	void getCommunityEmails_withInvalidCity_returnBAD_REQUEST()
			throws Exception {
		// Act
		mvc.perform(get("/CommunityEmail")
				.contentType(MediaType.APPLICATION_JSON).param("city", " "))// assert
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid city"));
	}
	@Test
	@Tag("getCommunityEmails")
	@DisplayName("getCommunityEmails should return email addresses of a community status OK")
	void getCommunityEmails_returnOK() throws Exception {
		// arrange
		HashSet<String> emails = new HashSet<>(Arrays.asList("boyd@gmail.com",
				"laod@gmail.com", "doe@gmail.com"));
		when(alertsService.getCommunityEmail(anyString())).thenReturn(emails);
		// Act
		mvc.perform(
				get("/CommunityEmail").contentType(MediaType.APPLICATION_JSON)
						.param("city", "Culver"))// assert
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(asJsonString(emails)));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
