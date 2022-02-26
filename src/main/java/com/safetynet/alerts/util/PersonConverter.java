package com.safetynet.alerts.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.safetynet.alerts.DTO.PersonDTO;
import com.safetynet.alerts.model.Person;
/**
 * PersonConverter a Person mapper
 * 
 * @author Fatima
 *
 */
@Component
public class PersonConverter {
	/**
	 * Create a SLF4J/LOG4J LOGGER instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonConverter.class);

	/**
	 * toPersonDTO map a given Person to PersonDTO Object
	 * 
	 * @param Person
	 * @return PersonDTO
	 */
	public PersonDTO toPersonDTO(Person person) {
		LOGGER.debug("mapping to PersonDTO for Person {}", person);
		return new PersonDTO(person.getFirstName(), person.getLastName(),
				person.getAddress(), person.getCity(), person.getZip(),
				person.getPhone(), person.getEmail());
	}

	/**
	 * toPerson map a given PersonDTO to Person Object
	 * 
	 * @param personDTO
	 * @return Person
	 */
	public Person toPerson(PersonDTO person) {
		LOGGER.debug("mapping to Person for PersonDTO {}", person);
		return new Person(person.getFirstName(), person.getLastName(),
				person.getAddress(), person.getCity(), person.getZip(),
				person.getPhone(), person.getEmail());

	}

	/**
	 * toPersonDTOList map a given Person objects list to PersonDTO Object list
	 * 
	 * @param list
	 *            of Person
	 * @return list of PersonDTO
	 */
	public List<PersonDTO> toPersonDTOList(List<Person> persons) {
		LOGGER.debug("mapping to list of PersonDTO for Persons {}", persons);

		List<PersonDTO> personsDTO = new ArrayList<>();
		persons.forEach(person -> personsDTO.add(toPersonDTO(person)));
		return personsDTO;
	}

}
