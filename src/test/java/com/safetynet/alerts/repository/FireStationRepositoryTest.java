package com.safetynet.alerts.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.safetynet.alerts.model.FireStation;

class FireStationRepositoryTest {
	private static FireStationRepository fireStationRepository;
	private static List<FireStation> fireStations = new ArrayList<>();
	static {
		fireStations.add(new FireStation("1509 Culver St", 3));
		fireStations.add(new FireStation("29 15th St", 2));
		fireStations.add(new FireStation("834 Binoc Ave", 3));
		fireStations.add(new FireStation("644 Gershwin Cir", 1));
		fireStations.add(new FireStation("748 Townings Dr", 3));
	}

	@BeforeEach
	void setUp() throws Exception {
		fireStationRepository = new FireStationRepository(fireStations);
	}

	@Test
	@Tag("findAll")
	@DisplayName("find all test should return a list of fireStation")
	void findAll_test() {
		// act
		List<FireStation> result = fireStationRepository.findAll();
		// assert
		assertThat(result).isEqualTo(fireStations);

	}

	@Test
	@Tag("save")
	@DisplayName("add fireStation test should add fireStation to fireStations list")
	void addFireStation_test() {
		// arrange
		FireStation fireStation = new FireStation("112 Steppes Pl", 4);
		// act
		fireStationRepository.addFireStation(fireStation);
		// assert
		assertThat(fireStations).contains(fireStation);

	}
	@Test
	@Tag("delete")
	@DisplayName("delete fireStation test should remove a fireStation from the list")
	void deleteFireStation_test() {
		// arrange
		FireStation fireStation = new FireStation("1509 Culver St", 3);
		// act
		fireStationRepository.deleteFireStation(fireStation);
		// assert
		assertThat(fireStations).doesNotContain(fireStation);

	}
	@Test
	@Tag("findbyAddress")
	@DisplayName("find fireStation by address test should return a fireStation with the given address")
	void FindByAddress_test() {
		// arrange
		FireStation fireStation = new FireStation("1509 Culver St", 3);
		// act
		FireStation result = fireStationRepository
				.findByAddress(fireStation.getAddress());
		// assert
		assertThat(result).isEqualTo(fireStation);

	}

	@Test
	@Tag("findbyStation")
	@DisplayName("find fireStations by Station test should return list of fireStation with the given station")
	void FindByStation_test() {
		// arrange
		FireStation fireStation = new FireStation("1509 Culver St", 3);
		// act
		List<String> result = fireStationRepository.findByStation(3);
		// assert
		assertThat(result).contains(fireStation.getAddress());

	}
	@Test
	@Tag("Update")
	@DisplayName("update fireStation test should Update and return the updated fireStation")
	void updateFireStation_test() {
		// arrange
		FireStation fireStation = new FireStation("748 Townings Dr", 1);
		// act
		FireStation result = fireStationRepository
				.updateFireStation(fireStation);
		// assert
		assertThat(result).isEqualTo(fireStation);

	}

}
