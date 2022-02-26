package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.DTO.PersonDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.model.Person;
/**
 * IPersonService interface for business processing of Person CRUD operations
 * 
 * @author Fatima
 *
 */
public interface IPersonService {

	/**
	 * to get all persons in data
	 * 
	 * @return list of persons
	 * @throws DataNotFoundException
	 */
	public List<PersonDTO> findAll() throws DataNotFoundException;
	/**
	 * findByName get person by given name
	 * 
	 * @param person's
	 *            firstName
	 * @param person's
	 *            lastName
	 * @return person
	 * @throws PersonNotFoundException
	 */
	public PersonDTO findByName(String firstName, String lastName)
			throws PersonNotFoundException;

	/**
	 * updatePerson update a given person
	 * 
	 * @param person
	 *            to update
	 * @return person updated
	 * @throws PersonNotFoundException
	 */
	public PersonDTO updatePerson(Person person) throws PersonNotFoundException;

	/**
	 * deletePerson delete person from data
	 * 
	 * @param person's
	 *            firstName
	 * @param person's
	 *            lastName
	 * @return person
	 * @throws PersonNotFoundException
	 */
	public PersonDTO deletePerson(String firstName, String lastName)
			throws PersonNotFoundException;
	/**
	 * finByAddress get list of persons living at the given address
	 * 
	 * @param address
	 * @return list of person
	 * @throws PersonNotFoundException
	 */
	public List<Person> findByAddress(String address)
			throws PersonNotFoundException;
	/**
	 * findPersonsByLastName get all persons with the given last name
	 * 
	 * @param person's
	 *            lastName
	 * @return list of person
	 * @throws PersonNotFoundException
	 */
	public List<Person> findPersonsByLastName(String lastName)
			throws PersonNotFoundException;
	/**
	 * findByCity get all persons living in given city
	 * 
	 * @param city
	 * @return list of person
	 * @throws PersonNotFoundException
	 */
	public List<Person> findByCity(String city) throws PersonNotFoundException;
	/**
	 * addPerson save a given person
	 * 
	 * @param personToAdd
	 * @return person saved
	 * @throws AlreadyExistsException
	 */
	public PersonDTO addPerson(Person personToAdd)
			throws AlreadyExistsException;
}
