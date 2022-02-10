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

	private IPersonRepository personRepository;
	private PersonConverter personConverter;
	@Autowired
	public PersonService(IPersonRepository personRepository,
			PersonConverter personConverter) {
		this.personRepository = personRepository;
		this.personConverter = personConverter;
	}

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
	public Person findByName(String firstName, String lastName)
			throws PersonNotFoundException {
		Person person = personRepository.findByName(firstName, lastName);
		if (person != null) {
			return person;
		}
		LOGGER.error("person {} {} not found", firstName, lastName);
		throw new PersonNotFoundException(
				"person " + firstName + " " + lastName + " not found");

	}

	@Override
	public PersonDTO deletePerson(String firstName, String lastName)
			throws PersonNotFoundException {
		Person person = findByName(firstName, lastName);
		personRepository.deletePerson(person);
		return personConverter.toPersonDTO(person);
	}

	@Override
	public void addPerson(Person personToAdd) throws AlreadyExistsException {
		Person person = personRepository.findByName(personToAdd.getFirstName(),
				personToAdd.getLastName());
		if (person == null) {
			personRepository.addPerson(personToAdd);
		}
		LOGGER.error("this person {} {} already exists",
				personToAdd.getFirstName(), personToAdd.getLastName());
		throw new AlreadyExistsException(
				"this person " + personToAdd.getFirstName() + " "
						+ personToAdd.getLastName() + " already exists");

	}

	@Override
	public PersonDTO updatePerson(Person personToUpdate)
			throws PersonNotFoundException {
		Person person = personRepository.findByName(
				personToUpdate.getFirstName(), personToUpdate.getLastName());
		if (person != null) {
			return personConverter
					.toPersonDTO(personRepository.updatePerson(personToUpdate));
		}
		LOGGER.error("the name cannot be changed");
		throw new PersonNotFoundException("the name cannot be changed");
	}

	@Override
	public List<Person> findByAddress(String address) {
		return personRepository.findByAddress(address);
	}

	@Override
	public List<Person> findPersonsByLastName(String lastName) {
		return personRepository.findPersonsByLastName(lastName);
	}

	@Override
	public List<Person> findByCity(String city) {
		return personRepository.findByCity(city);
	}

}
