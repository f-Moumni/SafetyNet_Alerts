package com.safetynet.alerts.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.google.inject.Inject;

public class AgeCalculatorTest {
	@Inject
	private AgeCalculator ageCalculator;

	@Test
	@Tag("calculate")
	@DisplayName("getPopulationCovredByStation test should return the age according to the given date of birth ")
	void calculate_Test() {
		// arrange
		String birthdate = "03/06/1984";
		// act
		int result = AgeCalculator.calculate(birthdate);
		// assert
		assertThat(result).isEqualTo(37);
	}
}
