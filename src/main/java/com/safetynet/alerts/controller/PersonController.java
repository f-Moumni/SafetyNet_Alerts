package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@RestController
public class PersonController {
	@Autowired
	PersonService personService;

	@GetMapping("/Persons")
	public ResponseEntity<List<Person>> getPersons() {
		if (personService.getPersons().isEmpty()) {
			return new ResponseEntity<>(personService.getPersons(),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(personService.getPersons(), HttpStatus.OK);
	}

	// // http://localhost:8080/person/person?firstName=toto&lastName=tata
	@GetMapping(value = "/Person/{firstName}&{lastName}")
	public Person getPerson(@PathVariable String firstName,
			@PathVariable String lastName) {

		return personService.getPerson(firstName, lastName);
	}
	//
	// @GetMapping
	// public Person getPerson2(@RequestBody Person person) {
	// return new Person();
	// }

}
