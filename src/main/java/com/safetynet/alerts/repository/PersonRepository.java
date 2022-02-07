package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepository implements IPersonRepository {

	private List<Person> persons = new ArrayList<>();

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
		Person person = null;
		for (Person pr : persons) {
			if ((pr.getFirstName().equalsIgnoreCase(firstName))
					&& (pr.getLastName().equalsIgnoreCase(lastName))) {
				person = pr;
				break;
			}
		}
		return person;

	}
	@Override
	public List<Person> findPersonsByLastName(String lastName) {
		List<Person> PersonsWithSameName = new ArrayList<Person>();
		for (Person pr : persons) {
			if (pr.getLastName().equalsIgnoreCase(lastName)) {
				PersonsWithSameName.add(pr);
			}
		}
		return PersonsWithSameName;

	}
	@Override
	public List<Person> findByAddress(String address) {
		List<Person> personsByAddress = new ArrayList<Person>();
		for (Person pr : persons) {
			if ((pr.getAddress().equalsIgnoreCase(address))) {
				personsByAddress.add(pr);
			}
		}
		return personsByAddress;

	}
	@Override
	public List<Person> findByCity(String city) {
		List<Person> personsByCity = new ArrayList<Person>();
		for (Person pr : persons) {
			if ((pr.getCity().equals(city))) {
				personsByCity.add(pr);
			}
		}
		return personsByCity;

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
