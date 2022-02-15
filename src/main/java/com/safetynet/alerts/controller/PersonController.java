package com.safetynet.alerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.DTO.PersonDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.service.IPersonService;
import com.safetynet.alerts.util.PersonConverter;

@RestController
@RequestMapping("/person")
@ResponseBody
public class PersonController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonController.class);
	@Autowired
	private IPersonService personService;
	@Autowired
	private PersonConverter personConverter;

	private final ResponseEntity<String> badRequestResponse = ResponseEntity
			.badRequest().body("Invalid name");

	@GetMapping("/persons")
	public ResponseEntity<List<PersonDTO>> getPersons()
			throws DataNotFoundException {
		LOGGER.debug("at getPersons methode ");
		List<PersonDTO> persons = personService.findAll();
		LOGGER.info("persons list getted with success   HttpStatus :{}",
				HttpStatus.OK);
		return new ResponseEntity<>(persons, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<?> getPersonByName(@RequestParam String firstName,
			@RequestParam String lastName) throws PersonNotFoundException {
		LOGGER.debug("at get Person By Name methode ");
		if (firstName.isBlank() || lastName.isBlank()) {
			LOGGER.error("Invalid Name  HttpStatus :{}",
					HttpStatus.BAD_REQUEST);
			return badRequestResponse;
		}
		PersonDTO person = personService.findByName(firstName, lastName);
		LOGGER.info("person {} {} getted with success   HttpStatus :{}",
				person.getFirstName(), person.getLastName(), HttpStatus.OK);
		return new ResponseEntity<>(person, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<?> deletePerson(@RequestParam String firstName,
			@RequestParam String lastName) throws PersonNotFoundException {
		LOGGER.debug("at deletePerson methode ");
		if (firstName.isBlank() || lastName.isBlank()) {
			LOGGER.error("Invalid name HttpStatus :{}", HttpStatus.BAD_REQUEST);
			return badRequestResponse;
		} else {
			PersonDTO person = personService.deletePerson(firstName, lastName);
			LOGGER.info("person {} {} deleted with success   HttpStatus :{}",
					person.getFirstName(), person.getLastName(), HttpStatus.OK);
			return new ResponseEntity<>(person, HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<?> addPerson(@RequestBody PersonDTO person)
			throws AlreadyExistsException {
		LOGGER.debug("at addPerson methode ");
		if (person.getFirstName().isBlank() || person.getLastName().isBlank()) {
			LOGGER.error("Invalid name  HttpStatus :{}",
					HttpStatus.BAD_REQUEST);
			return badRequestResponse;
		} else {
			PersonDTO personSaved = personService
					.addPerson(personConverter.toPerson(person));
			LOGGER.info("person {} {} saved with success   HttpStatus :{}",
					person.getFirstName(), person.getLastName(),
					HttpStatus.CREATED);
			return new ResponseEntity<>(personSaved, HttpStatus.CREATED);
		}
	}

	@PutMapping
	public ResponseEntity<?> updatePerson(@RequestBody PersonDTO person)
			throws PersonNotFoundException {
		LOGGER.debug("at updatePerson methode ");
		if (person.getFirstName().isBlank() || person.getLastName().isBlank()) {
			LOGGER.error("Invalid name  HttpStatus :{}",
					HttpStatus.BAD_REQUEST);
			return badRequestResponse;
		} else {
			PersonDTO pers = personService
					.updatePerson(personConverter.toPerson(person));

			LOGGER.info("person {} {} Updated with success   HttpStatus :{}",
					person.getFirstName(), person.getLastName(), HttpStatus.OK);
			return new ResponseEntity<>(pers, HttpStatus.OK);
		}

	}
}
