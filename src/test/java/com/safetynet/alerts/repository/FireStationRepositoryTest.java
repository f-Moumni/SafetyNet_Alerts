package com.safetynet.alerts.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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
	void findAll_test() {
		// act
		List<FireStation> result = fireStationRepository.findAll();
		// assert
		assertThat(result).isEqualTo(fireStations);

	}
	@Test
	void addFireStation_test() {
		// arrange
		FireStation fireStation = new FireStation("112 Steppes Pl", 4);
		// act
		fireStationRepository.addFireStation(fireStation);

		// assert
		assertThat(fireStations).contains(fireStation);

	}
	@Test
	void deleteFireStation_test() {
		// arrange
		FireStation fireStation = new FireStation("1509 Culver St", 3);
		// act
		fireStationRepository.deleteFireStation(fireStation);
		// assert
		assertThat(fireStations).doesNotContain(fireStation);

	}
	@Test
	void FindByAddress_test() {
		// arrange
		FireStation fireStation = new FireStation("1509 Culver St", 3);
		// act
		FireStation result = fireStationRepository
				.FindByAddress(fireStation.getAddress());
		// assert
		assertThat(result).isEqualTo(fireStation);

	}
	@Test
	void FindByStation_test() {
		// arrange
		FireStation fireStation = new FireStation("1509 Culver St", 3);
		// act
		List<String> result = fireStationRepository.FindByStation(3);
		// assert
		assertThat(result).contains(fireStation.getAddress());

	}
	@Test
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
