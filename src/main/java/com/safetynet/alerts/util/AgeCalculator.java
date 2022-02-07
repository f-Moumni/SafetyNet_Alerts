package com.safetynet.alerts.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AgeCalculator {

	public static long calculate(String birthdate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date = LocalDate.parse(birthdate, formatter);
		LocalDate now = LocalDate.now();
		Period period = Period.between(now, date);
		return Math.abs(period.getYears());

	}

}
