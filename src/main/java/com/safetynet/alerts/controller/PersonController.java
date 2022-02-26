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
/**
 * PersonController lass, allows to use administrative endpoints for CRUD
 * operations
 * 
 * @author Fatima
 * @see IPersonService
 * @see PersonConverter
 */
@RestController
@RequestMapping("/person")
@ResponseBody
public class PersonController {
	/**
	 * a SLF4J/LOG4J LOGGER instance
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonController.class);
	/**
	 * IPersonService the interface of service class to manage Persons
	 * 
	 */
	@Autowired
	private IPersonService personService;
	/**
	 * PersonConverter Person object mapper
	 */
	@Autowired
	private PersonConverter personConverter;
	/**
	 * badRequestResponse Response for bad Requests with invalid name message
	 */
	private final ResponseEntity<Object> badRequestResponse = ResponseEntity
			.badRequest().body("Invalid name");
	/**
	 * getPersons Get request to get all persons
	 * 
	 * @return ResponseEntity list of PersonDTO object or an error message in
	 *         case of error
	 * @throws DataNotFoundException
	 */
	@GetMapping("/persons")
	public ResponseEntity<List<PersonDTO>> getPersons()
			throws DataNotFoundException {
		LOGGER.debug("Get mapping getPersons ");
		List<PersonDTO> persons = personService.findAll();
		LOGGER.info("persons list getted with success   HttpStatus :{}",
				HttpStatus.OK);
		return new ResponseEntity<>(persons, HttpStatus.OK);
	}
	/**
	 * getPersonByName Get request to get person by first and last name
	 * 
	 * @param firstName
	 * @param lastName
	 * @return ResponseEntity of PersonDTO object or an error message in case of
	 *         error
	 * @throws PersonNotFoundException
	 */
	@GetMapping
	public ResponseEntity<Object> getPersonByName(
			@RequestParam String firstName, @RequestParam String lastName)
			throws PersonNotFoundException {
		LOGGER.debug("Get mapping Person By Name for {} {} ", firstName,
				lastName);
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
	/**
	 * deletePerson Delete request to delete given person
	 * 
	 * @param firstName
	 * @param lastName
	 * @return ResponseEntity of PersonDTO deleted or an error message in case
	 *         of error
	 * @throws PersonNotFoundException
	 */
	@DeleteMapping
	public ResponseEntity<Object> deletePerson(@RequestParam String firstName,
			@RequestParam String lastName) throws PersonNotFoundException {
		LOGGER.debug("Delete mapping for {} {} ", firstName, lastName);
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
	/**
	 * addPerson POST request to save a person
	 * 
	 * @param person
	 * @return ResponseEntity of PersonDTO object of person saved or an error
	 *         message in case of error
	 * @throws AlreadyExistsException
	 */
	@PostMapping
	public ResponseEntity<Object> addPerson(@RequestBody PersonDTO person)
			throws AlreadyExistsException {
		LOGGER.debug("Post mapping addPerson {} ", person);
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
	/**
	 * updatePerson PUT request to update a given person
	 * 
	 * @param person
	 * @returnResponseEntity of PersonDTO object of person updated or an error
	 *                       message in case of error
	 * @throws PersonNotFoundException
	 */
	@PutMapping
	public ResponseEntity<Object> updatePerson(@RequestBody PersonDTO person)
			throws PersonNotFoundException {
		LOGGER.debug("Put mapping updatePerson {} ", person);
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
