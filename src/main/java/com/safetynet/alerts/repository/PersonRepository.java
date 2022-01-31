package com.safetynet.alerts.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.data.ReadDataFromJson;
import com.safetynet.alerts.model.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PersonRepository {

	@Autowired
	ReadDataFromJson readData;

	public List<Person> findAll() {
		log.info("display of ALL persons");
		return readData.getListPersons();
	}

	public Person findByName(String firstName, String lastName) {
		for (Person pr : readData.getListPersons()) {
			if ((pr.getFirstName() == firstName)
					&& (pr.getLastName() == lastName)) {
				return pr;
			}
		}
		return null;

	}

}
