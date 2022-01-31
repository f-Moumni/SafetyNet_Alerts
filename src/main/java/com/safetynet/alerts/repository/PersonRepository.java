package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.data.IReadData;
import com.safetynet.alerts.model.Person;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Data
@Slf4j
@Repository
public class PersonRepository {

	private List<Person> listPersons = new ArrayList<>();
	private final IReadData readData;
	@Autowired
	public PersonRepository(final IReadData readData) {
		log.debug("getting persons list in respository");
		this.readData = readData;
		listPersons.addAll(readData.getListPersons());
	}

	public List<Person> findAll() {

		return listPersons;
	}

	public Person findByName(String firstName, String lastName) {
		Person person = null;
		for (Person pr : listPersons) {
			if ((pr.getFirstName() == firstName)
					&& (pr.getLastName() == lastName)) {
				person = pr;
			}
		}
		return person;

	}

}
