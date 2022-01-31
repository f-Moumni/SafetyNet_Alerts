package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

import lombok.Data;

@Data
@Service
public class PersonService implements IPersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Person> findAll() {

		return personRepository.findAll();
	}
	@Override
	public Person findByName(String firstName, String lastName) {
		return personRepository.findByName(firstName, lastName);

	}
}
