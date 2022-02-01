package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface IPersonRepository {

	void addPerson(Person person);

	List<Person> findAll();

}
