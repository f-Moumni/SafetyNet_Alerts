package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface IPersonRepository {

	public void addPerson(Person person);

	public List<Person> findAll();

	public void deletePerson(Person person);

	Person findByName(String firstName, String lastName);

	Person updatePerson(Person person);

}
