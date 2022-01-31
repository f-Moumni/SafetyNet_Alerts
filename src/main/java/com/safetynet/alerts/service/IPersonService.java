package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface IPersonService {
	public List<Person> findAll();
	public Person findByName(String firstName, String lastName);
}
