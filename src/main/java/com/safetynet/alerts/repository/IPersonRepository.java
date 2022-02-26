package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Person;
/**
 * IPersonRepository interface allows to manage Person data
 * 
 * @author fatima
 *
 */
public interface IPersonRepository {
	/**
	 * addPerson to save a given Person
	 * 
	 * @param person
	 *            to save
	 */
	public void addPerson(Person person);

	/**
	 * 
	 * findAll to get all fire Persons in data
	 * 
	 * @return list of Persons
	 * 
	 */
	public List<Person> findAll();

	/**
	 * delete a given Person from data
	 * 
	 * @param person
	 *            to delete
	 */
	public void deletePerson(Person person);

	/**
	 * get Person with a given name
	 * 
	 * @param firstName
	 * @param lastName
	 * @return person
	 */
	public Person findByName(String firstName, String lastName);

	/**
	 * update a given Person in data
	 * 
	 * @param person
	 *            to update
	 * 
	 * @return person updated
	 */
	public Person updatePerson(Person person);

	/**
	 * get Person with a given address
	 * 
	 * @param address
	 * @return person
	 */
	public List<Person> findByAddress(String address);

	/**
	 * get list of persons with same last name
	 * 
	 * @param lastName
	 * @return list of persons
	 */
	public List<Person> findPersonsByLastName(String lastName);

	/**
	 * get all person's living in a given city
	 * 
	 * @param city
	 * @return list of persons
	 */
	public List<Person> findByCity(String city);

}
