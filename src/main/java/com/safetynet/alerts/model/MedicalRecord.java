package com.safetynet.alerts.model;

import java.util.List;

import lombok.Data;

@Data
public class MedicalRecord {

	private String firstName;
	private String lastName;
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/DD/YYYY")
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;

}
