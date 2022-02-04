package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.DTO.PersonDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.BadRequestException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;
import com.safetynet.alerts.util.PersonConverter;

@RestController
@RequestMapping("/person")
public class PersonController {

	private final IPersonService personService;
	@Autowired
	private PersonConverter personConverter;
	@Autowired
	public PersonController(final IPersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/persons")
	public ResponseEntity<List<PersonDTO>> getPersons()
			throws DataNotFoundException {
		List<PersonDTO> persons = personService.findAll();
		return new ResponseEntity<>(persons, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<PersonDTO> getPersonByName(
			@RequestParam String firstName, @RequestParam String lastName)
			throws PersonNotFoundException, BadRequestException {
		if (firstName.isBlank() || lastName.isBlank()) {
			throw new BadRequestException(
					"firstName and lastName are required");
		} else {
			PersonDTO person = personConverter
					.toPersonDTO(personService.findByName(firstName, lastName));
			return new ResponseEntity<>(person, HttpStatus.OK);
		}
	}

	@DeleteMapping
	public ResponseEntity<PersonDTO> deletePerson(
			@RequestParam String firstName, @RequestParam String lastName)
			throws PersonNotFoundException, BadRequestException {
		if (((firstName == null) || (lastName == null)) || (firstName.isBlank())
				|| (lastName.isBlank())) {
			throw new BadRequestException(
					"firstName and lastName are required");
		} else {
			PersonDTO person = personService.deletePerson(firstName, lastName);
			return new ResponseEntity<>(person, HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<PersonDTO> addPerson(@RequestBody Person person)
			throws AlreadyExistsException, BadRequestException {
		if (((person.getFirstName() == null) || (person.getLastName() == null))
				|| (person.getFirstName().isBlank())
				|| (person.getLastName().isBlank())) {
			throw new BadRequestException(
					"the first and the last name are required");
		} else {

			personService.addPerson(person);
			return new ResponseEntity<>(personConverter.toPersonDTO(person),
					HttpStatus.CREATED);
		}
	}

	@PutMapping
	public ResponseEntity<PersonDTO> updatePerson(@RequestBody Person person)
			throws PersonNotFoundException, BadRequestException {
		if (((person.getFirstName() == null) || (person.getLastName() == null))
				|| (person.getFirstName().isBlank())
				|| (person.getLastName().isBlank())) {
			throw new BadRequestException(
					"the first and the last name are required");
		} else {

			return new ResponseEntity<>(personService.updatePerson(person),
					HttpStatus.OK);
		}

	}
}
