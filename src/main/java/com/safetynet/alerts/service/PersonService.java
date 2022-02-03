package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Person> findAll() {

		return personRepository.findAll();

	}

	@Override
	public Person findByName(String firstName, String lastName)
			throws PersonNotFoundException {
		Person person = personRepository.findByName(firstName, lastName);
		if (person != null) {
			return person;
		} else {
			throw new PersonNotFoundException("person not found");
		}
	}
	@Override
	public Person deletePerson(String firstName, String lastName)
			throws PersonNotFoundException {
		try {
			Person person = findByName(firstName, lastName);
			personRepository.deletePerson(person);
			return person;
		} catch (PersonNotFoundException e) {
			throw new PersonNotFoundException("person not found");
		}

	}

	@Override
	public void addPerson(Person personToAdd) throws AlreadyExistsException {
		Person person = personRepository.findByName(personToAdd.getFirstName(),
				personToAdd.getLastName());
		if (person == null) {
			personRepository.addPerson(personToAdd);
		} else {
			throw new AlreadyExistsException("this person already exists");
		}

	}

	@Override
	public Person updatePerson(Person personToUpdate)
			throws PersonNotFoundException {
		Person person = personRepository.findByName(
				personToUpdate.getFirstName(), personToUpdate.getLastName());
		if (person != null) {
			return personRepository.updatePerson(personToUpdate);
		} else {
			throw new PersonNotFoundException("the name cannot be changed");
		}
	}

}
