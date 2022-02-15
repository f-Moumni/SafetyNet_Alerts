package com.safetynet.alerts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.DTO.PersonDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.IPersonRepository;
import com.safetynet.alerts.util.PersonConverter;

@Service
public class PersonService implements IPersonService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonService.class);
	@Autowired
	private IPersonRepository personRepository;
	@Autowired
	private PersonConverter personConverter;

	@Override
	public List<PersonDTO> findAll() throws DataNotFoundException {
		List<Person> persons = personRepository.findAll();
		if (persons != null) {
			return personConverter.toPersonDTOList(persons);
		}
		LOGGER.error("Person data not found");
		throw new DataNotFoundException("Person data not found");
	}

	@Override
	public PersonDTO findByName(String firstName, String lastName)
			throws PersonNotFoundException {
		Person person = personRepository.findByName(firstName, lastName);
		if (person != null) {
			return personConverter.toPersonDTO(person);
		}
		LOGGER.error("person {} {} not found", firstName, lastName);
		throw new PersonNotFoundException(
				"person " + firstName + " " + lastName + " not found");

	}

	@Override
	public PersonDTO deletePerson(String firstName, String lastName)
			throws PersonNotFoundException {
		Person person = personRepository.findByName(firstName, lastName);
		if (person != null) {
			personRepository.deletePerson(person);
			return personConverter.toPersonDTO(person);
		}
		LOGGER.error("person {} {} not found", firstName, lastName);
		throw new PersonNotFoundException(
				"person " + firstName + " " + lastName + " not found");
	}

	@Override
	public PersonDTO addPerson(Person personToAdd)
			throws AlreadyExistsException {
		Person person = personRepository.findByName(personToAdd.getFirstName(),
				personToAdd.getLastName());
		if (person == null) {
			personRepository.addPerson(personToAdd);
			return personConverter.toPersonDTO(personToAdd);
		} else {
			LOGGER.error("this person {} {} already exists",
					personToAdd.getFirstName(), personToAdd.getLastName());
			throw new AlreadyExistsException(
					"this person " + personToAdd.getFirstName() + " "
							+ personToAdd.getLastName() + " already exists");
		}

	}

	@Override
	public PersonDTO updatePerson(Person person)
			throws PersonNotFoundException {
		Person personToUpdate = personRepository
				.findByName(person.getFirstName(), person.getLastName());
		if (personToUpdate != null) {
			return personConverter
					.toPersonDTO(personRepository.updatePerson(person));
		}
		LOGGER.error("the name cannot be changed");
		throw new PersonNotFoundException("the name cannot be changed");
	}

	@Override
	public List<Person> findByAddress(String address)
			throws PersonNotFoundException {
		List<Person> persons = personRepository.findByAddress(address);
		if (persons != null) {
			return persons;
		}
		LOGGER.error("there is no person at this address : {}", address);
		throw new PersonNotFoundException(
				"there is no person at this address " + address);
	}

	@Override
	public List<Person> findPersonsByLastName(String lastName)
			throws PersonNotFoundException {
		List<Person> persons = personRepository.findPersonsByLastName(lastName);
		if (persons != null) {
			return persons;
		}
		LOGGER.error("there is no person with this last name : {}", lastName);
		throw new PersonNotFoundException(
				"there is no person with this last name " + lastName);

	}

	@Override
	public List<Person> findByCity(String city) throws PersonNotFoundException {

		List<Person> persons = personRepository.findByCity(city);
		if (persons != null) {
			return persons;
		}
		LOGGER.error("there is no person in this city : {}", city);
		throw new PersonNotFoundException(
				"there is no person in this city " + city);
	}

}
