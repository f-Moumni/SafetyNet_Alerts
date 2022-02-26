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
/**
 * PersonService class for business processing of Person CRUD operations
 * 
 * @author Fatima
 *
 */
@Service
public class PersonService implements IPersonService {

	/**
	 * Create a SLF4J/LOG4J LOGGER instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonService.class);

	/**
	 * IPersonRepository implement class reference.
	 */
	@Autowired
	private IPersonRepository personRepository;

	/**
	 * PersonConverter person mapper
	 */
	@Autowired
	private PersonConverter personConverter;

	/**
	 * to get all persons in data
	 * 
	 * @return list of persons
	 * @throws DataNotFoundException
	 */
	@Override
	public List<PersonDTO> findAll() throws DataNotFoundException {
		LOGGER.debug(" find All persons start");
		LOGGER.info(" getting all persons ");
		List<Person> persons = personRepository.findAll();
		if (persons != null) {
			return personConverter.toPersonDTOList(persons);
		}
		LOGGER.error("Person data not found");
		throw new DataNotFoundException("Person data not found");
	}

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
	@Override
	public PersonDTO findByName(String firstName, String lastName)
			throws PersonNotFoundException {
		LOGGER.debug(" find By Name start");
		LOGGER.info(" getting person {} {} ", firstName, lastName);
		Person person = personRepository.findByName(firstName, lastName);
		if (person != null) {
			return personConverter.toPersonDTO(person);
		}
		LOGGER.error("person {} {} not found", firstName, lastName);
		throw new PersonNotFoundException(
				"person " + firstName + " " + lastName + " not found");
	}

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
	@Override
	public PersonDTO deletePerson(String firstName, String lastName)
			throws PersonNotFoundException {
		LOGGER.debug(" delete person start");
		LOGGER.info(" deleting person {} {} ", firstName, lastName);
		Person person = personRepository.findByName(firstName, lastName);
		if (person != null) {
			personRepository.deletePerson(person);
			return personConverter.toPersonDTO(person);
		}
		LOGGER.error("person {} {} not found", firstName, lastName);
		throw new PersonNotFoundException(
				"person " + firstName + " " + lastName + " not found");
	}
	/**
	 * addPerson save a given person
	 * 
	 * @param personToAdd
	 * @return person saved
	 * @throws AlreadyExistsException
	 */
	@Override
	public PersonDTO addPerson(Person personToAdd)
			throws AlreadyExistsException {
		LOGGER.debug(" add person start");
		LOGGER.info(" saving person {}  ", personToAdd);
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

	/**
	 * updatePerson update a given person
	 * 
	 * @param person
	 *            to update
	 * @return person updated
	 * @throws PersonNotFoundException
	 */
	@Override
	public PersonDTO updatePerson(Person person)
			throws PersonNotFoundException {
		LOGGER.debug(" update  person start");
		LOGGER.info(" updating person {}  ", person);
		Person personToUpdate = personRepository
				.findByName(person.getFirstName(), person.getLastName());
		if (personToUpdate != null) {
			return personConverter
					.toPersonDTO(personRepository.updatePerson(person));
		}
		LOGGER.error("the name cannot be changed");
		throw new PersonNotFoundException("the name cannot be changed");
	}

	/**
	 * finByAddress get list of persons living at the given address
	 * 
	 * @param address
	 * @return list of person
	 * @throws PersonNotFoundException
	 */
	@Override
	public List<Person> findByAddress(String address)
			throws PersonNotFoundException {
		LOGGER.debug(" find by address start");
		LOGGER.info(" find person at address{}  ", address);
		List<Person> persons = personRepository.findByAddress(address);
		if (persons != null) {
			return persons;
		}
		LOGGER.error("there is no person at this address : {}", address);
		throw new PersonNotFoundException(
				"there is no person at this address " + address);
	}

	/**
	 * findPersonsByLastName get all persons with the given last name
	 * 
	 * @param person's
	 *            lastName
	 * @return list of person
	 * @throws PersonNotFoundException
	 */
	@Override
	public List<Person> findPersonsByLastName(String lastName)
			throws PersonNotFoundException {
		LOGGER.debug(" find by last name start");
		LOGGER.info(" find persons with lastName{}  ", lastName);
		List<Person> persons = personRepository.findPersonsByLastName(lastName);
		if (persons != null) {
			return persons;
		}
		LOGGER.error("there is no person with this last name : {}", lastName);
		throw new PersonNotFoundException(
				"there is no person with this last name " + lastName);

	}

	/**
	 * findByCity get all persons living in given city
	 * 
	 * @param city
	 * @return list of person
	 * @throws PersonNotFoundException
	 */
	@Override
	public List<Person> findByCity(String city) throws PersonNotFoundException {
		LOGGER.debug(" find by city start");
		LOGGER.info(" find person living in city {}  ", city);
		List<Person> persons = personRepository.findByCity(city);
		if (persons != null) {
			return persons;
		}
		LOGGER.error("there is no person in this city : {}", city);
		throw new PersonNotFoundException(
				"there is no person in this city " + city);
	}

}
