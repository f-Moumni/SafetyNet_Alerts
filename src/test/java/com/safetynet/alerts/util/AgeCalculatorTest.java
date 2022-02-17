package com.safetynet.alerts.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AgeCalculatorTest {

	@Test
	@Tag("calculate")
	@DisplayName("calculate_Test test should return the age according to the given date of birth ")
	void calculate_Test() {
		// arrange
		String birthdate = "03/06/1984";
		// act
		int result = AgeCalculator.calculate(birthdate);
		// assert
		assertThat(result).isEqualTo(37);
	}

}
