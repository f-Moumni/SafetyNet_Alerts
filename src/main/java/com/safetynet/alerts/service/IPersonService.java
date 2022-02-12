package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.DTO.PersonDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.model.Person;

public interface IPersonService {

	public List<PersonDTO> findAll() throws DataNotFoundException;

	public PersonDTO findByName(String firstName, String lastName)
			throws PersonNotFoundException;

	public void addPerson(Person person) throws AlreadyExistsException;

	public PersonDTO updatePerson(Person person) throws PersonNotFoundException;

	public PersonDTO deletePerson(String firstName, String lastName)
			throws PersonNotFoundException;

	public List<Person> findByAddress(String address)
			throws PersonNotFoundException;

	public List<Person> findPersonsByLastName(String lastName)
			throws PersonNotFoundException;

	public List<Person> findByCity(String city) throws PersonNotFoundException;
}
