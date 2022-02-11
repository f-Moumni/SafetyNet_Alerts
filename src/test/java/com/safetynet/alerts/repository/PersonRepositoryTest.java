package com.safetynet.alerts.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetynet.alerts.model.Person;

class PersonRepositoryTest {
	private static PersonRepository personRepository;
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
		List<Person> result = personRepository.findAll();
		// assert
		assertThat(result).isEqualTo(persons);
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
		Person person = new Person("Sophia", "Zemicks", "892 Downing Ct",
				"Culver", 97451, "841-874-7878", "soph@email.com");
		// act
		Person result = personRepository.findByName(person.getFirstName(),
				person.getLastName());
		// assert
		assertThat(result).isEqualTo(person);

	}
	@Test
	void findPersonsByLastName_test() {
		// arrange
		Person person = new Person("Jacob", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-6513", "drk@email.com");

		// act
		List<Person> result = personRepository.findPersonsByLastName("Boyd");
		// assert
		assertThat(result).contains(person);
	}
	@Test
	void findByCityTest() {
		// arrange
		Person person = new Person("Jacob", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-6513", "drk@email.com");
		// act
		List<Person> result = personRepository.findByCity("Culver");
		// assert
		assertThat(result).contains(person);
	}
	@Test
	void updatePersonTest() {
		// arrange
		Person person = new Person("John", "Boyd", "2000 Culver st", "Culver",
				97451, "841-874-2222", "jboyd@email.com");
		// act
		Person result = personRepository.updatePerson(person);
		// assert
		assertThat(result).isEqualTo(person);

	}
	@Test
	void findByAddressTest() {
		// arrange
		Person person = new Person("Jacob", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-6513", "drk@email.com");
		// act
		List<Person> result = personRepository
				.findByAddress(person.getAddress());
		// assert
		assertThat(result).contains(person);

	}

}
