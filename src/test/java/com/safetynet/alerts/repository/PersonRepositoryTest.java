package com.safetynet.alerts.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
	@Tag("findAll")
	@DisplayName("find all test should return a list of person")
	void findAll_test() {
		// act
		List<Person> result = personRepository.findAll();
		// assert
		assertThat(result).isEqualTo(persons);
	}

	@Test
	@Tag("save")
	@DisplayName("add person test should add person to persons list")
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
	@Tag("delete")
	@DisplayName("delete person test should remove a person from the list")
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
	@Tag("findbyName")
	@DisplayName("find person by name test should return a person with the given name")
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
	@Tag("findbyName")
	@DisplayName("find person by name test with an unregistered person should return null")
	void findPersonByName_test_withAnUnregisteredPerson_shouldReturnNull() {
		// act
		Person result = personRepository.findByName("lola", "laod");
		// assert
		assertThat(result).isNull();

	}
	@Test
	@Tag("findbyName")
	@DisplayName("find person by last name test should return list of person with the given last name")
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
	@Tag("findbyName")
	@DisplayName("find person by last name test no person with the given last name should return EmptyList")
	void findPersonsByLastName_withNon_ExistentName_returnEmptyList() {
		// act
		List<Person> result = personRepository.findPersonsByLastName("Laod");
		// assert
		assertThat(result).isEmpty();
	}
	@Test
	@Tag("findbyCity")
	@DisplayName("find person by city test should return a list of person living in the given city")
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
	@Tag("findbyCity")
	@DisplayName("find person by city test with an unregistered City should return empty list")
	void findByCityTest_withAnUnregisteredCity_returnEmptyList() {
		// act
		List<Person> result = personRepository.findByCity("Londres");
		// assert
		assertThat(result).isEmpty();
	}
	@Test
	@Tag("Update")
	@DisplayName("update person test should Update and return the updated person")
	void updatePerson_Test() {
		// arrange
		Person person = new Person("John", "Boyd", "2000 Culver st", "Culver",
				97451, "841-874-2222", "jboyd@email.com");
		// act
		Person result = personRepository.updatePerson(person);
		// assert
		assertThat(result).isEqualTo(person);

	}
	@Test
	@Tag("Update")
	@DisplayName("update person test with unregistered person should return null")
	void updatePerson_withAnUnregisteredPerson_retunNull() {
		// arrange
		Person person = new Person("John", "Doe", "2000 Culver st", "Culver",
				97451, "841-874-2222", "jboyd@email.com");
		// act
		Person result = personRepository.updatePerson(person);
		// assert
		assertThat(result).isNull();

	}
	@Test
	@Tag("findByAdress")
	@DisplayName("find person by address test should return a list of person living in the given address")
	void findByAddress_Test() {
		// arrange
		Person person = new Person("Jacob", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-6513", "drk@email.com");
		// act
		List<Person> result = personRepository
				.findByAddress(person.getAddress());
		// assert
		assertThat(result).contains(person);

	}
	@Test
	@Tag("findByAdress")
	@DisplayName("find person by address test with unregistered address should return empty list")
	void findByAddress_withAnUnregisteredAddress_retunNull() {
		// act
		List<Person> result = personRepository
				.findByAddress("152 francisco st");
		// assert
		assertThat(result).isEmpty();

	}

}
