package com.safetynet.alerts.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetynet.alerts.model.Person;

class PersonRepositoryTest {
	private static IPersonRepository personRepository;
	private static List<Person> persons = new ArrayList<>();
	static {
		persons.add(new Person("John", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-6512", "jaboyd@email.com"));
		persons.add(new Person("Jacob", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-6513", "drk@email.com"));
		persons.add(new Person("Tessa", "Carman", "834 Binoc Ave", "Culver",
				97451, "841-874-6512", "tenz@email.com"));
		persons.add(new Person("Sophia", "Zemicks", "892 Downing Ct", "Culver",
				97451, "841-874-7878", "soph@email.com"));
	}
	@BeforeEach
	void setUp() throws Exception {
		personRepository = new PersonRepository(persons);

	}

	@Test
	void findAll_test() {
		// act
		List<Person> Result = personRepository.findAll();
		// assert
		assertThat(Result).isEqualTo(persons);
	}
	@Test
	void addPerson_test() {
		// arrange
		Person person = new Person("Roger", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-6544", "rogerboyd@email.com");
		// act
		personRepository.addPerson(person);
		// assert
		assertThat(persons).contains(person);
	}
	@Test

	void deletePerson_test() {
		// arrange
		Person person = new Person("John", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-6512", "jaboyd@email.com");
		// act
		personRepository.deletePerson(person);
		// assert
		assertThat(persons).doesNotContain(person);
	}
	@Test
	void findPersonByName_test() {
		// arrange
		String firstName = "John";
		String lastName = "Boyd";
		// act
		Person result = personRepository.findByName(firstName, lastName);
		// assert
		assertThat(result.getFirstName()).isEqualTo(firstName);
		assertThat(result.getLastName()).isEqualTo(lastName);

	}
	@Test
	void findPersonsByLastName_test() {
		// arrange
		String lastName = "Boyd";
		// act
		List<Person> result = personRepository.findPersonsByLastName(lastName);
		// assert

	}

}
