package com.safetynet.alerts.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/**
 * AgeCalculator allow to calculate age of person
 * 
 * @author Fatima
 *
 */
@Component
public class AgeCalculator {

	/**
	 * Create a SLF4J/LOG4J LOGGER instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AgeCalculator.class);
	/**
	 * calculate calculate age for a given birthdate
	 * 
	 * @param birthdate
	 * @return age
	 */
	public static int calculate(String birthdate) {
		LOGGER.debug(" calculating the age for birthdate {}", birthdate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date = LocalDate.parse(birthdate, formatter);
		LocalDate now = LocalDate.now();
		Period period = Period.between(now, date);
		return Math.abs(period.getYears());

	}

}
