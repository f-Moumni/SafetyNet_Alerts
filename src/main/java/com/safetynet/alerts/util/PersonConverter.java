package com.safetynet.alerts.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.safetynet.alerts.DTO.PersonDTO;
import com.safetynet.alerts.model.Person;
@Component
public class PersonConverter {

	public PersonDTO toPersonDTO(Person person) {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setFirstName(person.getFirstName());
		personDTO.setLastName(person.getLastName());
		personDTO.setAddress(person.getAddress());
		personDTO.setPhone(person.getPhone());
		personDTO.setCity(person.getCity());
		personDTO.setZip(person.getZip());
		return personDTO;

	}
	public Person toPerson(PersonDTO personDTO) {
		Person person = new Person();
		person.setFirstName(personDTO.getFirstName());
		person.setLastName(personDTO.getLastName());
		person.setAddress(personDTO.getAddress());
		person.setPhone(personDTO.getPhone());
		person.setCity(personDTO.getCity());
		person.setZip(personDTO.getZip());
		return person;

	}
	public List<PersonDTO> toListOfPersonDTO(List<Person> persons) {
		List<PersonDTO> personsDTO = new ArrayList<PersonDTO>();
		for (Person person : persons) {
			personsDTO.add(toPersonDTO(person));
		}
		return personsDTO;
	}

}
