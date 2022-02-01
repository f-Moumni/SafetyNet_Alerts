package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

import lombok.Data;
@Data

@Repository
public class PersonRepository implements IPersonRepository {

	private List<Person> listPersons = new ArrayList<>();

	@Override
	public List<Person> findAll() {

		return listPersons;
	}
	@Override
	public void addPerson(Person person) {
		this.listPersons.add(person);
	}

}
