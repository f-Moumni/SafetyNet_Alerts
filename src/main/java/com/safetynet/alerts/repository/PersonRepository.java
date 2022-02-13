package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepository implements IPersonRepository {

	private List<Person> persons = new ArrayList<>();

	@Autowired
	public PersonRepository(List<Person> persons) {
		this.persons = persons;
	}
	@Override
	public List<Person> findAll() {
		return persons;
	}
	@Override
	public void addPerson(Person person) {
		this.persons.add(person);
	}
	@Override
	public void deletePerson(Person person) {
		this.persons.remove(person);
	}
	@Override
	public Person findByName(String firstName, String lastName) {
		return persons.stream()
				.filter(person -> person.getLastName()
						.equalsIgnoreCase(lastName))
				.filter(person -> person.getFirstName()
						.equalsIgnoreCase(firstName))
				.collect(Collectors.toList()).get(0);

	}
	@Override
	public List<Person> findPersonsByLastName(String lastName) {

		return persons.stream().filter(
				person -> person.getLastName().equalsIgnoreCase(lastName))
				.collect(Collectors.toList());

	}
	@Override
	public List<Person> findByAddress(String address) {
		return persons.stream()
				.filter(person -> person.getAddress().equalsIgnoreCase(address))
				.collect(Collectors.toList());

	}
	@Override
	public List<Person> findByCity(String city) {
		return persons.stream()
				.filter(person -> person.getCity().equalsIgnoreCase(city))
				.collect(Collectors.toList());

	}
	@Override
	public Person updatePerson(Person person) {
		Person persontoUpdate = findByName(person.getFirstName(),
				person.getLastName());
		deletePerson(persontoUpdate);
		addPerson(person);
		return person;

	}

}
