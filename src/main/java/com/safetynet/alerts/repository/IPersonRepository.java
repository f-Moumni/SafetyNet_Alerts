package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface IPersonRepository {

	public void addPerson(Person person);

	public List<Person> findAll();

	public void deletePerson(Person person);

	public Person findByName(String firstName, String lastName);

	public Person updatePerson(Person person);

	public List<Person> findByAddress(String address);

	public List<Person> findPersonsByLastName(String lastName);

	public List<Person> findByCity(String city);

}
