package com.safetynet.alerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alerts.DTO.FireStationDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.FireStationNotFoundException;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.IFireStationRepository;
import com.safetynet.alerts.util.FireStationConverter;
@ExtendWith(MockitoExtension.class)
class FireStationServiceTest {
	@InjectMocks
	private static FireStationService fireStationService;
	@Mock
	private static IFireStationRepository fireStationRepository;
	@Mock
	private static FireStationConverter fireStationConverter;
	private static List<FireStation> fireStations = new ArrayList<>();
	static {
		fireStations.add(new FireStation("1509 Culver St", 3));
		fireStations.add(new FireStation("29 15th St", 2));
		fireStations.add(new FireStation("834 Binoc Ave", 3));
		fireStations.add(new FireStation("644 Gershwin Cir", 1));

	}
	private static List<FireStationDTO> fireStationsDTO = new ArrayList<>();
	static {
		fireStationsDTO.add(new FireStationDTO("1509 Culver St", 3));
		fireStationsDTO.add(new FireStationDTO("29 15th St", 2));
		fireStationsDTO.add(new FireStationDTO("834 Binoc Ave", 3));
		fireStationsDTO.add(new FireStationDTO("644 Gershwin Cir", 1));

	}
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Tag("findAll")
	@DisplayName("find all test should return a list of fireStation")
	void findAll_test_shouldReturnListOffireStation()
			throws DataNotFoundException {
		// arrange
		when(fireStationRepository.findAll()).thenReturn(fireStations);
		when(fireStationConverter.toFireStationDTOList(fireStations))
				.thenReturn(fireStationsDTO);
		// act
		List<FireStationDTO> result = fireStationService.findAll();
		// assert
		verify(fireStationRepository).findAll();
		assertThat(result).isEqualTo(fireStationsDTO);
	}
	@Test
	@Tag("findAll")
	@DisplayName("find all fireStations test with empty data should Throw DataNotFoundException")
	void findAll_test_withEmptyDate_shouldThrowDataNotFoundException()
			throws DataNotFoundException {
		// arrange
		when(fireStationRepository.findAll()).thenReturn(null);
		// assert
		assertThrows(DataNotFoundException.class,
				() -> fireStationService.findAll());
		verify(fireStationRepository).findAll();

	}
	@Test
	@Tag("save")
	@DisplayName("add fireStation test should Add a fireStation to the list")
	void addFireStation_test_shouldSaveFireStation()
			throws AlreadyExistsException {
		// arrange
		FireStation fireStation = new FireStation("748 Townings Dr", 3);
		when(fireStationRepository.FindByAddress(anyString())).thenReturn(null);
		// act
		fireStationService.addFireStation(fireStation);
		// assert
		verify(fireStationRepository).addFireStation(fireStation);

	}
	@Test
	@Tag("save")
	@DisplayName("add fireStation test with a fireStation already registered should throw AlreadyExistsException")
	void addFireStation_test_withFireStationAlredyRegistred_shouldThrowAlreadyExistsException()
			throws AlreadyExistsException {
		// arrange
		FireStation fireStation = fireStations.get(0);
		when(fireStationRepository.FindByAddress(anyString()))
				.thenReturn(fireStation);
		// assert
		assertThrows(AlreadyExistsException.class,
				() -> fireStationService.addFireStation(fireStation));

	}
	@Test
	@Tag("findByAddress")
	@DisplayName("find by Address test should return a fireStation with the given address")
	void findByAddress_test_ShouldReturnFireStation()
			throws FireStationNotFoundException {
		// arrange
		FireStation fireStation = fireStations.get(0);
		when(fireStationRepository.FindByAddress(anyString()))
				.thenReturn(fireStation);
		// Act
		FireStation result = fireStationService
				.FindByAddress(fireStation.getAddress());
		// assert
		assertThat(result).isEqualTo(fireStation);
		verify(fireStationRepository).FindByAddress(anyString());

	}
	@Test
	@Tag("findByAddress")
	@DisplayName("find by Address test with no firestation with the given address should throw FireStationNotFoundException ")
	void findByAddress_test_ShouldThrowFireStationNotFoundException()
			throws FireStationNotFoundException {
		// arrange
		FireStation fireStation = fireStations.get(0);
		when(fireStationRepository.FindByAddress(anyString())).thenReturn(null);
		// assert
		assertThrows(FireStationNotFoundException.class,
				() -> fireStationService
						.FindByAddress(fireStation.getAddress()));
		verify(fireStationRepository).FindByAddress(anyString());
	}
	@Test
	@Tag("update")
	@DisplayName("update fireStation test with an unregistered fireStation should Throw FireStationNotFoundException")
	void updateFireStation_test_WithAnUnregisteredFireStation_shouldThrowFireStationNotFoundException()
			throws FireStationNotFoundException {
		// arrange
		FireStation fireStation = new FireStation("1509 Culver St", 2);
		when(fireStationRepository.FindByAddress(anyString())).thenReturn(null);
		// assert
		assertThrows(FireStationNotFoundException.class,
				() -> fireStationService.updateFireStation(fireStation));
		verify(fireStationRepository).FindByAddress(anyString());

	}
	@Test
	@Tag("update")
	@DisplayName("update fireStation test should update a fireStation and return person updated")
	void updateFireStation_test_shouldUpdateFireStation_ReturnFireStationUpdated()
			throws FireStationNotFoundException {
		// arrange
		FireStation fireStation = new FireStation("1509 Culver St", 2);
		FireStation fireStationToUpdate = fireStations.get(0);
		FireStationDTO fireStationUpdated = new FireStationDTO("1509 Culver St",
				2);
		when(fireStationRepository.FindByAddress(anyString()))
				.thenReturn(fireStationToUpdate);
		when(fireStationRepository.updateFireStation(fireStation))
				.thenReturn(fireStation);
		when(fireStationConverter.toFireStationDTO(fireStation))
				.thenReturn(fireStationUpdated);
		// act
		FireStationDTO result = fireStationService
				.updateFireStation(fireStation);
		// assert
		assertThat(result).isEqualTo(fireStationUpdated);
		verify(fireStationRepository).updateFireStation(fireStation);
		verify(fireStationRepository).FindByAddress(anyString());
	}
	@Test
	@Tag("delete")
	@DisplayName("delete fireStation test should remove a fireStation from the list and return fireStation deleted")
	void deleteFireStation_test_shouldRemoveFireStation_ReturnFireStationDeleted()
			throws FireStationNotFoundException {
		// arrange
		FireStation fireStation = fireStations.get(0);
		FireStationDTO fireStationDTO = fireStationsDTO.get(0);
		when(fireStationRepository.FindByAddress(anyString()))
				.thenReturn(fireStation);
		when(fireStationConverter.toFireStationDTO(fireStation))
				.thenReturn(fireStationDTO);
		// act
		FireStationDTO result = fireStationService
				.deleteFireStation(fireStation.getAddress());
		// assert
		assertThat(result).isEqualTo(fireStationDTO);
		verify(fireStationRepository).deleteFireStation(fireStation);

	}
	@Test
	@Tag("delete")
	@DisplayName("delete fireStation test with an unregistered fireStation should Throw FireStationNotFoundException")
	void deleteFireStation_test_WithAnUnregisteredFireStation_shouldThrowFireStationNotFoundException()
			throws FireStationNotFoundException {
		// arrange
		FireStation fireStation = fireStations.get(0);
		when(fireStationRepository.FindByAddress(anyString())).thenReturn(null);
		// assert
		assertThrows(FireStationNotFoundException.class,
				() -> fireStationService
						.deleteFireStation(fireStation.getAddress()));
	}
	@Test
	@Tag("findByStation")
	@DisplayName("find by Station test should return a list of firestations addresses with given station number")
	void findBystation_test_shouldReturnListOfFireStationsAdresses()
			throws FireStationNotFoundException {
		// arrange
		List<String> addresses = new ArrayList<String>(
				List.of("1509 Culver St", "834 Binoc Ave"));

		when(fireStationRepository.FindByStation(3)).thenReturn(addresses);
		// act
		List<String> result = fireStationService.FindByStation(3);
		// assert
		assertThat(result).containsAll(addresses);
		verify(fireStationRepository).FindByStation(any(Integer.class));
	}
	@Test
	@Tag("findByStation")
	@DisplayName("find by Station test with an unknown station should throw FireStationNotFoundException")
	void findBystation_test_shouldThrowFireStationNotFoundException()
			throws FireStationNotFoundException {
		// arrange
		List<String> addresses = new ArrayList<String>();
		when(fireStationRepository.FindByStation(8)).thenReturn(addresses);
		// assert
		assertThrows(FireStationNotFoundException.class,
				() -> fireStationService.FindByStation(8));
		verify(fireStationRepository).FindByStation(any(Integer.class));
	}
}
