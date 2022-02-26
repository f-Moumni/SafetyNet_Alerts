package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;
/**
 * PersonRepository class allows o store and manage Person data
 * 
 * @author fatima
 *
 */
@Repository
public class PersonRepository implements IPersonRepository {

	/**
	 * Create a SLF4J/LOG4J LOGGER instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonRepository.class);

	/**
	 * Persons data list
	 */

	private List<Person> persons = new ArrayList<>();
	/**
	 * a class Constructor
	 * 
	 * @param persons
	 */

	@Autowired
	public PersonRepository(List<Person> persons) {
		this.persons = persons;

	}

	/**
	 * 
	 * findAll to get all fire Persons in data
	 * 
	 * @return list of Persons
	 * 
	 */
	@Override
	public List<Person> findAll() {
		LOGGER.debug("Getting perosn form data");
		return persons;
	}

	/**
	 * addPerson to save a given Person
	 * 
	 * @param person
	 *            to save
	 */
	@Override
	public void addPerson(Person person) {
		LOGGER.debug("saving person in data {}", person);
		this.persons.add(person);
	}

	/**
	 * delete a given Person from data
	 * 
	 * @param person
	 *            to delete
	 */
	@Override
	public void deletePerson(Person person) {
		LOGGER.debug("deleting person from data {}", person);
		this.persons.remove(person);
	}

	/**
	 * get Person with a given name
	 * 
	 * @param firstName
	 * @param lastName
	 * @return person
	 */
	@Override
	public Person findByName(String firstName, String lastName) {
		LOGGER.debug("search person {} {} ", firstName, lastName);
		return persons.stream()
				.filter(person -> person.getLastName()
						.equalsIgnoreCase(lastName))
				.filter(person -> person.getFirstName()
						.equalsIgnoreCase(firstName))
				.findFirst().orElse(null);

	}

	/**
	 * get list of persons with same last name
	 * 
	 * @param lastName
	 * @return list of persons
	 */
	@Override
	public List<Person> findPersonsByLastName(String lastName) {
		LOGGER.debug("search for persons with {} ", lastName);
		return persons.stream().filter(
				person -> person.getLastName().equalsIgnoreCase(lastName))
				.collect(Collectors.toList());

	}

	/**
	 * get Person with a given address
	 * 
	 * @param address
	 * @return person
	 */
	@Override
	public List<Person> findByAddress(String address) {
		LOGGER.debug("search for persons living at {} ", address);
		return persons.stream()
				.filter(person -> person.getAddress().equalsIgnoreCase(address))
				.collect(Collectors.toList());

	}

	/**
	 * get all person's living in a given city
	 * 
	 * @param city
	 * @return list of persons
	 */
	@Override
	public List<Person> findByCity(String city) {
		LOGGER.debug("search for persons living in {} city", city);
		return persons.stream()
				.filter(person -> person.getCity().equalsIgnoreCase(city))
				.collect(Collectors.toList());

	}

	/**
	 * update a given Person in data
	 * 
	 * @param person
	 *            to update
	 * 
	 * @return person updated
	 */
	@Override
	public Person updatePerson(Person person) {
		LOGGER.debug("updating person {}", person);
		Person persontoUpdate = findByName(person.getFirstName(),
				person.getLastName());
		if (persontoUpdate != null) {
			deletePerson(persontoUpdate);
			addPerson(person);
			return person;
		}
		return null;

	}

}
