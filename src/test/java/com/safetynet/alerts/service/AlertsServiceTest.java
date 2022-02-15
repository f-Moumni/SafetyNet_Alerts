package com.safetynet.alerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alerts.DTO.ChildAlertDTO;
import com.safetynet.alerts.DTO.ChildDTO;
import com.safetynet.alerts.DTO.CoveredPopulationDTO;
import com.safetynet.alerts.DTO.FireDTO;
import com.safetynet.alerts.DTO.FloodDTO;
import com.safetynet.alerts.DTO.PersonAlertDTO;
import com.safetynet.alerts.DTO.PersonDTO;
import com.safetynet.alerts.DTO.PersonInfosDTO;
import com.safetynet.alerts.exceptions.FireStationNotFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.util.AgeCalculator;
@ExtendWith(MockitoExtension.class)
class AlertsServiceTest {

	@Mock
	private static IPersonService personService;
	@Mock
	private static IFireStationService fireStationService;
	@Mock
	private static IMedicalRecordService medicalRecordService;
	@InjectMocks
	private static AlertService alertService;
	private static List<Person> persons = new ArrayList<>();
	static {
		persons.add(new Person("John", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-6512", "jaboyd@email.com"));
		persons.add(new Person("Jacob", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-6513", "drk@email.com"));
		persons.add(new Person("Tessa", "Carman", "1509 Culver st", "Culver",
				97451, "841-874-6512", "tenz@email.com"));
		persons.add(new Person("Sophia", "Zemicks", "892 Downing Ct", "Culver",
				97451, "841-874-7878", "soph@email.com"));
	}
	private static List<FireStation> fireStations = new ArrayList<>();
	static {
		fireStations.add(new FireStation("1509 Culver St", 3));
		fireStations.add(new FireStation("29 15th St", 2));
		fireStations.add(new FireStation("644 Gershwin Cir", 1));

	}
	private static List<MedicalRecord> medicalRecords = new ArrayList<>();
	static {

		medicalRecords.add(new MedicalRecord("John", "Boyd", "03/06/1984",
				new ArrayList<String>(
						List.of("aznol:350mg", "hydrapermazol:100mg")),
				new ArrayList<String>(List.of("nillacilan"))));
		medicalRecords.add(new MedicalRecord("Jacob", "Boyd", "03/06/1989",
				new ArrayList<String>(List.of("pharmacol:5000mg",
						"terazine:10mg", "noznazol:250mg")),
				new ArrayList<String>()));
		medicalRecords.add(new MedicalRecord("Eric", "Cadigan", "08/06/1945",
				new ArrayList<String>(List.of("tradoxidine:400mg")),
				new ArrayList<String>()));
		medicalRecords.add(new MedicalRecord("Tessa", "Carman", "03/06/2018",
				new ArrayList<String>(
						List.of("ibupurin:200mg", "hydrapermazol:400mg")),
				new ArrayList<String>(List.of("nillacilan"))));
	}
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Tag("PopulationCovredByStation")
	@DisplayName("getPopulationCovredByStation test should return number of adults, number of children and all de population covred by given fire station number")
	void getPopulationCovredByStation_Test()
			throws FireStationNotFoundException, PersonNotFoundException,
			MedicalRecordNotFoundException {
		// arrange
		List<PersonAlertDTO> personsCouvred = new ArrayList<>(List.of(
				new PersonAlertDTO("John", "Boyd", "1509 Culver st",
						"841-874-6512"),
				new PersonAlertDTO("Tessa", "Carman", "1509 Culver st",
						"841-874-6512")));// persons covered

		when(fireStationService.findByStation(any(Integer.class)))
				.thenReturn(new ArrayList<String>(List.of("1509 Culver St")));

		when(personService.findByAddress(anyString())).thenReturn(
				new ArrayList<Person>(List.of(persons.get(0), persons.get(2))));

		when(medicalRecordService.findByName("John", "Boyd"))
				.thenReturn(medicalRecords.get(0));
		when(medicalRecordService.findByName("Tessa", "Carman"))
				.thenReturn(medicalRecords.get(3));
		try (MockedStatic<AgeCalculator> ageCalculator = Mockito
				.mockStatic(AgeCalculator.class)) {
			ageCalculator.when(() -> AgeCalculator.calculate("03/06/1984"))
					.thenReturn(35);
			ageCalculator.when(() -> AgeCalculator.calculate("03/01/2018"))
					.thenReturn(4);
		}
		CoveredPopulationDTO couverdP = new CoveredPopulationDTO(personsCouvred,
				1, 1);
		// act
		CoveredPopulationDTO result = alertService
				.getPopulationCovredByStation(3);
		// assert
		assertThat(result.getNumberOfAdults())
				.isEqualTo(couverdP.getNumberOfAdults());
		assertThat(result.getNumberOfChildren())
				.isEqualTo(couverdP.getNumberOfChildren());
		assertThat(result.getPersonsCouverd().size())
				.isEqualTo(couverdP.getPersonsCouverd().size());
		verify(fireStationService).findByStation(any(Integer.class));
		verify(personService).findByAddress(anyString());
		verify(medicalRecordService, times(2)).findByName(anyString(),
				anyString());

	}
	@Test
	@Tag("ChildrenByAddress")
	@DisplayName("getChildrenByAddress test should return all children and their families at a given address")
	void getChildrenByAddress_Test()
			// arrange
			throws PersonNotFoundException, MedicalRecordNotFoundException {
		when(personService.findByAddress(anyString()))
				.thenReturn(new ArrayList<Person>(List.of(persons.get(0),
						persons.get(1), persons.get(2))));

		when(medicalRecordService.findByName("John", "Boyd"))
				.thenReturn(medicalRecords.get(0));
		when(medicalRecordService.findByName("Jacob", "Boyd"))
				.thenReturn(medicalRecords.get(1));
		when(medicalRecordService.findByName("Tessa", "Carman"))
				.thenReturn(medicalRecords.get(3));

		try (MockedStatic<AgeCalculator> ageCalculator = Mockito
				.mockStatic(AgeCalculator.class)) {
			ageCalculator.when(() -> AgeCalculator.calculate("03/06/1984"))
					.thenReturn(35);
			ageCalculator.when(() -> AgeCalculator.calculate("03/06/2018"))
					.thenReturn(3);
			ageCalculator.when(() -> AgeCalculator.calculate("03/06/1989"))
					.thenReturn(32);
		}
		// act
		ChildAlertDTO result = alertService
				.getChildrenByAddress("1509 Culver St");
		// assert
		assertThat(result.getFamily()).containsAll(
				new ArrayList<String>(List.of("John Boyd", "Jacob Boyd")));
		assertThat(result.getChildren().toString())
				.contains(new ChildDTO("Tessa", "Carman", 3).toString());
		verify(personService).findByAddress(anyString());
		verify(medicalRecordService, times(3)).findByName(anyString(),
				anyString());
	}
	@Test
	@Tag("ChildrenByAddress")
	@DisplayName("getChildrenByAddress test with no childen at a given address return null")
	void getChildrenByAddress_withNochliden_Test()
			throws PersonNotFoundException, MedicalRecordNotFoundException {
		// arrange
		when(personService.findByAddress(anyString())).thenReturn(
				new ArrayList<Person>(List.of(persons.get(0), persons.get(1))));

		when(medicalRecordService.findByName("John", "Boyd"))
				.thenReturn(medicalRecords.get(0));
		when(medicalRecordService.findByName("Jacob", "Boyd"))
				.thenReturn(medicalRecords.get(1));

		try (MockedStatic<AgeCalculator> ageCalculator = Mockito
				.mockStatic(AgeCalculator.class)) {
			ageCalculator.when(() -> AgeCalculator.calculate("03/06/1984"))
					.thenReturn(35);
			ageCalculator.when(() -> AgeCalculator.calculate("03/06/1989"))
					.thenReturn(32);
		}
		// act
		ChildAlertDTO result = alertService
				.getChildrenByAddress("1509 Culver St");
		// assert
		assertThat(result).isNull();
		verify(personService).findByAddress(anyString());
		verify(medicalRecordService, times(2)).findByName(anyString(),
				anyString());
	}

	@Test
	@Tag("PhoneNumberByStation")
	@DisplayName("getPhoneNumberByStation test should return a list of phone numbers of people covered by the given station")
	void getPhoneNumberByStation_Test() throws FireStationNotFoundException,
			PersonNotFoundException, MedicalRecordNotFoundException {
		// arrange
		when(fireStationService.findByStation(any(Integer.class)))
				.thenReturn(new ArrayList<String>(List.of("1509 Culver St")));
		when(personService.findByAddress(anyString())).thenReturn(
				new ArrayList<Person>(List.of(persons.get(0), persons.get(2))));
		when(medicalRecordService.findByName(anyString(), anyString()))
				.thenReturn(medicalRecords.get(0));
		// art
		HashSet<String> result = alertService.getPhoneNumberByStation(3);
		// assert
		assertThat(result).containsOnly("841-874-6512");
		verify(personService).findByAddress(anyString());
		verify(fireStationService).findByStation(3);

	}
	@Test
	@Tag("InhabitantByAddress")
	@DisplayName("getInhabitantByAddress test should return the list of inhabitants living at the given address and the number of station serving if")
	void getInhabitantByAddress_Test() throws PersonNotFoundException,
			FireStationNotFoundException, MedicalRecordNotFoundException {
		// arrange
		when(personService.findByAddress(anyString())).thenReturn(
				new ArrayList<Person>(List.of(persons.get(0), persons.get(1))));
		when(fireStationService.findByAddress(anyString()))
				.thenReturn(fireStations.get(0));
		when(medicalRecordService.findByName("John", "Boyd"))
				.thenReturn(medicalRecords.get(0));
		when(medicalRecordService.findByName("Jacob", "Boyd"))
				.thenReturn(medicalRecords.get(1));
		try (MockedStatic<AgeCalculator> ageCalculator = Mockito
				.mockStatic(AgeCalculator.class)) {
			ageCalculator.when(() -> AgeCalculator.calculate("03/06/1984"))
					.thenReturn(35);
			ageCalculator.when(() -> AgeCalculator.calculate("03/06/1989"))
					.thenReturn(32);
		}
		// act
		FireDTO result = alertService.getInhabitantByAddress("1509 Culver St");
		// assert
		assertThat(result.getStation()).isEqualTo(3);
		assertThat(result.getInhabitants().size()).isEqualTo(2);
		verify(personService).findByAddress(anyString());
		verify(medicalRecordService, times(2)).findByName(anyString(),
				anyString());
	}

	@Test
	@Tag("FloodsByStation")
	@DisplayName("getFloodsByStation test should return a list of all households served by the given station")
	void getFloodsByStation_Test() throws FireStationNotFoundException,
			MedicalRecordNotFoundException, PersonNotFoundException {
		// arrange
		when(fireStationService.findByStation(any(Integer.class)))
				.thenReturn(new ArrayList<String>(List.of("1509 Culver St")));

		when(personService.findByAddress(anyString())).thenReturn(
				new ArrayList<Person>(List.of(persons.get(0), persons.get(1))));
		when(fireStationService.findByAddress(anyString()))
				.thenReturn(fireStations.get(0));
		when(medicalRecordService.findByName("John", "Boyd"))
				.thenReturn(medicalRecords.get(0));
		when(medicalRecordService.findByName("Jacob", "Boyd"))
				.thenReturn(medicalRecords.get(1));
		try (MockedStatic<AgeCalculator> ageCalculator = Mockito
				.mockStatic(AgeCalculator.class)) {
			ageCalculator.when(() -> AgeCalculator.calculate("03/06/1984"))
					.thenReturn(35);
			ageCalculator.when(() -> AgeCalculator.calculate("03/06/1989"))
					.thenReturn(32);
		}
		// act
		List<FloodDTO> result = alertService.getFloodsByStation(3);
		// assert
		assertThat(result.size()).isEqualTo(1);
		assertThat(result.get(0).getAddress()).isEqualTo("1509 Culver St");
		assertThat(result.get(0).getInhabitants().size()).isEqualTo(2);
		verify(fireStationService).findByStation(3);
		verify(personService).findByAddress(anyString());
		verify(medicalRecordService, times(2)).findByName(anyString(),
				anyString());
	}
	@Test
	@Tag("PersonInfosByName")
	@DisplayName("getPersonInfosByName test should return the information and the medical record of all the people who have the given name")
	void getPersonInfosByName_Test()
			throws PersonNotFoundException, MedicalRecordNotFoundException {
		// arrange
		when(personService.findByName(anyString(), anyString()))
				.thenReturn(new PersonDTO("John", "Boyd", "1509 Culver st",
						"Culver", 97451, "841-874-6512", "jaboyd@email.com"));
		when(personService.findPersonsByLastName(anyString())).thenReturn(
				new ArrayList<Person>(List.of(persons.get(0), persons.get(1))));
		when(medicalRecordService.findByName("John", "Boyd"))
				.thenReturn(medicalRecords.get(0));
		when(medicalRecordService.findByName("Jacob", "Boyd"))
				.thenReturn(medicalRecords.get(1));
		try (MockedStatic<AgeCalculator> ageCalculator = Mockito
				.mockStatic(AgeCalculator.class)) {
			ageCalculator.when(() -> AgeCalculator.calculate("03/06/1984"))
					.thenReturn(35);
			ageCalculator.when(() -> AgeCalculator.calculate("03/06/1989"))
					.thenReturn(32);
		}
		// act
		List<PersonInfosDTO> result = alertService.getPersonInfosByName("john",
				"Boyd");
		// assert
		assertThat(result.size()).isEqualTo(2);
		assertThat(result.get(0).getLastName()).isEqualTo("Boyd");
		assertThat(result.get(1).getLastName()).isEqualTo("Boyd");
		verify(medicalRecordService, times(2)).findByName(anyString(),
				anyString());
		verify(personService).findPersonsByLastName("Boyd");
		verify(personService).findByName(anyString(), anyString());

	}
	@Test
	@Tag("CommunityEmail")
	@DisplayName("getCommunityEmail test should return the e-mail addresses of all the inhabitants of the given city.")
	void getCommunityEmail_Test() throws PersonNotFoundException {
		// arrange
		when(personService.findByCity(anyString())).thenReturn(persons);
		// act
		HashSet<String> result = alertService.getCommunityEmail("Culver");
		// assert
		assertThat(result)
				.containsAll(new ArrayList<String>(List.of("jaboyd@email.com",
						"soph@email.com", "tenz@email.com", "drk@email.com")));
		verify(personService).findByCity(anyString());
	}
}
