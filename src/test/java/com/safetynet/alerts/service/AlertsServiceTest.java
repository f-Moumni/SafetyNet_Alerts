package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
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
		persons.add(new Person("Tessa", "Carman", "834 Binoc Ave", "Culver",
				97451, "841-874-6512", "tenz@email.com"));
		// persons.add(new Person("Sophia", "Zemicks", "892 Downing Ct",
		// "Culver",
		// 97451, "841-874-7878", "soph@email.com"));
	}
	private static List<FireStation> fireStations = new ArrayList<>();
	static {
		fireStations.add(new FireStation("1509 Culver St", 3));
		fireStations.add(new FireStation("29 15th St", 2));
		fireStations.add(new FireStation("834 Binoc Ave", 3));
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
		medicalRecords.add(new MedicalRecord("Clive", "Ferguson", "03/06/1994",
				new ArrayList<String>(
						List.of("ibupurin:200mg", "hydrapermazol:400mg")),
				new ArrayList<String>(List.of("nillacilan"))));
	}
	@BeforeEach
	void setUp() throws Exception {
	}

	// @Test
	// @DisplayName("getPopulationCovredByStation test should return
	// CoveredPopulationDTO by given fire station number")
	// void
	// throws FireStationNotFoundException, PersonNotFoundException {
	// // arrange
	// List<String> addresses = new ArrayList<String>(
	// List.of("1509 Culver St", "834 Binoc Ave"));
	// when(fireStationService.FindByStation(any(Integer.class)))
	// .thenReturn(addresses);
	// when(personService.findByAddress(any(String.class)))
	// .thenReturn(persons);
	// }

}
