package com.safetynet.alerts.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.safetynet.alerts.DTO.FireStationDTO;
import com.safetynet.alerts.model.FireStation;

public class FireStationConverterTest {

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
		fireStationConverter = new FireStationConverter();
	}
	@Test
	@Tag("toFireStationDTO")
	@DisplayName("toFireStationDTO test should return FireSationDTO of given FireStation")
	void toFireStationDTO_Test() {
		// arrange
		FireStation fireStation = fireStations.get(0);
		// act
		FireStationDTO result = fireStationConverter
				.toFireStationDTO(fireStation);
		// assert
		assertThat(result).usingRecursiveComparison()
				.isEqualTo(fireStationsDTO.get(0));
	}
	@Test
	@Tag("toFireStation")
	@DisplayName("toFireStation test should return FireSation of given FireStationDTO")
	void toFireStation_Test() {
		// arrange
		FireStationDTO fireStationDTO = fireStationsDTO.get(0);
		// act
		FireStation result = fireStationConverter.toFireStation(fireStationDTO);
		// assert
		assertThat(result).usingRecursiveComparison()
				.isEqualTo(fireStations.get(0));
	}

	@Test
	@Tag("toFireStationDTOList")
	@DisplayName("toFireStationDTOList test should return list of FireSationDTO of given list FireStation")
	void toFireStationDTOlist_Test() {

		// act
		List<FireStationDTO> result = fireStationConverter
				.toFireStationDTOList(fireStations);
		// assert
		assertThat(result).usingRecursiveComparison()
				.isEqualTo(fireStationsDTO);

	}
}
