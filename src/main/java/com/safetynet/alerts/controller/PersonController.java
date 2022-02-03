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

import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.BadRequestException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	private final IPersonService personService;

	@Autowired
	public PersonController(final IPersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getPersons()
			throws DataNotFoundException {
		List<Person> persons = personService.findAll();
		if (persons != null) {
			return new ResponseEntity<>(persons, HttpStatus.OK);
		} else
			throw new DataNotFoundException("Person data not found");

	}

	@GetMapping
	public ResponseEntity<Person> getPersonByName(
			@RequestParam String firstName, @RequestParam String lastName)
			throws PersonNotFoundException, BadRequestException {
		if (firstName.isEmpty() || lastName.isEmpty()) {
			throw new BadRequestException(
					"firstName and lastName are required");
		} else {
			try {
				Person person = personService.findByName(firstName, lastName);
				return new ResponseEntity<>(person, HttpStatus.OK);
			} catch (PersonNotFoundException e) {
				throw new PersonNotFoundException(
						"person:" + firstName + " " + lastName + " not found");
			}
		}

	}
	@DeleteMapping
	public ResponseEntity<Person> deletePerson(@RequestParam String firstName,
			@RequestParam String lastName)
			throws PersonNotFoundException, BadRequestException {
		if (firstName.equals(null) || lastName.equals(null)) {
			throw new BadRequestException(
					"firstName and lastName are required");
		} else {
			try {
				Person person = personService.deletePerson(firstName, lastName);
				return new ResponseEntity<>(person, HttpStatus.OK);
			} catch (PersonNotFoundException e) {
				throw new PersonNotFoundException(
						"person: " + firstName + " " + lastName + " not found");
			}
		}
	}

	@PostMapping
	public ResponseEntity<Person> addPerson(@RequestBody Person person)
			throws AlreadyExistsException, BadRequestException {

		if (person != null) {
			try {
				personService.addPerson(person);
				return new ResponseEntity<>(person, HttpStatus.CREATED);
			} catch (AlreadyExistsException e) {
				throw new AlreadyExistsException(
						"person: " + person.getFirstName() + " "
								+ person.getLastName() + " exists already");
			}
		} else {
			throw new BadRequestException("All filed are required");
		}
	}

	@PutMapping
	public ResponseEntity<Person> updatePerson(@RequestBody Person person)
			throws PersonNotFoundException, BadRequestException {
		if (person != null) {
			try {
				return new ResponseEntity<>(personService.updatePerson(person),
						HttpStatus.OK);
			} catch (PersonNotFoundException e) {
				throw new PersonNotFoundException("person: "
						+ person.getFirstName() + " " + person.getLastName()
						+ " not found. the name cannot be changed");
			}
		} else {
			throw new BadRequestException("All filed are required");
		}

	}
}
