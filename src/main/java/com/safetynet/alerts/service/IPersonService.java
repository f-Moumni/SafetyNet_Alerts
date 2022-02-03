package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.model.Person;

public interface IPersonService {

	public List<Person> findAll();

	public Person findByName(String firstName, String lastName)
			throws PersonNotFoundException;

	Person deletePerson(String firstName, String lastName)
			throws PersonNotFoundException;

	public void addPerson(Person person) throws AlreadyExistsException;

	public Person updatePerson(Person person) throws PersonNotFoundException;
}
