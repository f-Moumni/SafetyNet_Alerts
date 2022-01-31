package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;

import lombok.Data;
@Data
@RestController
public class PersonController {

	private final IPersonService personService;

	@Autowired
	public PersonController(final IPersonService personService) {
		this.personService = personService;
	}

	@GetMapping(value = "person")
	public ResponseEntity<List<Person>> getPersons() {
		if (personService.findAll().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
		}
	}

	// // http://localhost:8080/person/person?firstName=toto&lastName=tata
	@GetMapping(value = "person/{firstName}/{lastName}")
	public ResponseEntity<Person> getPerson(@PathVariable String firstName,
			@PathVariable String lastName) {
		Person person = personService.findByName(firstName, lastName);
		if (person == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(
					personService.findByName(firstName, lastName),
					HttpStatus.OK);
		}

	}
	//
	// @GetMapping
	// public Person getPerson2(@RequestBody Person person) {
	// return new Person();
	// }

}
