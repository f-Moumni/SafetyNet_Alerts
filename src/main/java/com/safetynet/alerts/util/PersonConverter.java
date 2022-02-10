package com.safetynet.alerts.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.safetynet.alerts.DTO.PersonDTO;
import com.safetynet.alerts.model.Person;
@Component
public class PersonConverter {

	public PersonDTO toPersonDTO(Person person) {
		return new PersonDTO(person.getFirstName(), person.getLastName(),
				person.getAddress(), person.getCity(), person.getZip(),
				person.getPhone(), person.getEmail());
	}

	public Person toPerson(PersonDTO person) {
		return new Person(person.getFirstName(), person.getLastName(),
				person.getAddress(), person.getCity(), person.getZip(),
				person.getPhone(), person.getEmail());

	}
	public List<PersonDTO> toPersonDTOList(List<Person> persons) {
		List<PersonDTO> personsDTO = new ArrayList<>();
		persons.forEach(person -> personsDTO.add(toPersonDTO(person)));
		return personsDTO;
	}

}
