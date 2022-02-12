package com.safetynet.alerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alerts.DTO.PersonDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.IPersonRepository;
import com.safetynet.alerts.util.PersonConverter;

import nl.altindag.log.LogCaptor;
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
	@InjectMocks
	private static PersonService personService;
	@Mock
	private static IPersonRepository personRepository;
	@Mock
	private static PersonConverter personConverter;
	private static LogCaptor logCaptor;

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
	private static List<PersonDTO> personsDTO = new ArrayList<>();
	static {
		personsDTO.add(new PersonDTO("John", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-6512", "jaboyd@email.com"));
		personsDTO.add(new PersonDTO("Jacob", "Boyd", "1509 Culver st",
				"Culver", 97451, "841-874-6513", "drk@email.com"));
		personsDTO.add(new PersonDTO("Tessa", "Carman", "834 Binoc Ave",
				"Culver", 97451, "841-874-6512", "tenz@email.com"));
		personsDTO.add(new PersonDTO("Sophia", "Zemicks", "892 Downing Ct",
				"Culver", 97451, "841-874-7878", "soph@email.com"));
	}

	@BeforeEach
	void setUp() throws Exception {
		logCaptor = LogCaptor.forName("PersonService");
		logCaptor.getDebugLogs();
	}

	@Test
	@Tag("findAll")
	@DisplayName("find all test should return a list of people")
	void findAll_test_shouldReturnListOfperson() throws DataNotFoundException {
		// arrange
		when(personRepository.findAll()).thenReturn(persons);
		when(personConverter.toPersonDTOList(persons)).thenReturn(personsDTO);
		// act
		List<PersonDTO> result = personService.findAll();
		// assert
		verify(personRepository).findAll();
		assertThat(result).isEqualTo(personsDTO);
	}
	@Test
	@Tag("findAll")
	@DisplayName("find all persons test with empty data should Throw DataNotFoundException")
	void findAll_test_withEmptyDate_shouldThrowDataNotFoundException()
			throws DataNotFoundException {
		// arrange
		when(personRepository.findAll()).thenReturn(null);

		// assert
		assertThrows(DataNotFoundException.class,
				() -> personService.findAll());
		verify(personRepository).findAll();
		// assertThat(logCaptor.getErrorLogs()).contains("Person data not
		// found");
	}
	@Test
	@Tag("findByName")
	@DisplayName("find by name test should return a person with the given name")
	void findByName_test_shouldReturnPerson() throws PersonNotFoundException {
		// arrange
		Person person = persons.get(0);
		PersonDTO personDTO = personsDTO.get(0);
		when(personRepository.findByName(anyString(), anyString()))
				.thenReturn(person);
		when(personConverter.toPersonDTO(person)).thenReturn(personDTO);
		// act
		PersonDTO result = personService.findByName(person.getFirstName(),
				person.getLastName());
		// assert
		verify(personRepository).findByName(anyString(), anyString());
		assertThat(result).isEqualTo(personDTO);

	}
	@Test
	@Tag("findByName")
	@DisplayName("find by name test with an unregistered person should Throw PersonNotFoundException")
	void findByName_test_WithAnUnregisteredPerson_shouldThrowPersonNotFoundException()
			throws PersonNotFoundException {
		// arrange
		when(personRepository.findByName(anyString(), anyString()))
				.thenReturn(null);
		// assert
		assertThrows(PersonNotFoundException.class,
				() -> personService.findByName(anyString(), anyString()));
		verify(personRepository).findByName(anyString(), anyString());

	}
	@Test
	@Tag("delete")
	@DisplayName("delete Person test with an unregistered person should Throw PersonNotFoundException")
	void deletePerson_test_WithAnUnregisteredPerson_shouldThrowPersonNotFoundException()
			throws PersonNotFoundException {
		// arrange
		Person person = persons.get(0);
		when(personRepository.findByName(anyString(), anyString()))
				.thenReturn(null);
		// assert
		assertThrows(PersonNotFoundException.class, () -> personService
				.deletePerson(person.getFirstName(), person.getLastName()));
	}
	@Test
	@Tag("delete")
	@DisplayName("delete Person test should remove a person from the data and return person deleted")
	void deletePerson_test_shouldRemovePerson_ReturnPersonDeleted()
			throws PersonNotFoundException {
		// arrange
		Person person = persons.get(0);
		PersonDTO personDTO = personsDTO.get(0);
		when(personRepository.findByName(anyString(), anyString()))
				.thenReturn(person);
		when(personConverter.toPersonDTO(person)).thenReturn(personDTO);
		// act
		PersonDTO result = personService.deletePerson(person.getFirstName(),
				person.getLastName());
		// assert
		assertThat(result).isEqualTo(personDTO);
		verify(personRepository).deletePerson(any(Person.class));

	}

	@Test
	@Tag("save")
	@DisplayName("add Person test should Add a person to data")
	void addPerson_test_shouldSavePerson() throws AlreadyExistsException {
		// arrange
		Person person = new Person("sofi", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-2222", "sarahboyd@email.com");
		when(personRepository.findByName(anyString(), anyString()))
				.thenReturn(null);
		// act
		personService.addPerson(person);
		// assert
		verify(personRepository).addPerson(person);

	}
	@Test
	@Tag("save")
	@DisplayName("add Person test with a person already registered should throw AlreadyExistsException")
	void addPerson_test_withPersonAlredyRegistred_shouldThrowAlreadyExistsException()
			throws AlreadyExistsException {
		// arrange
		Person person = persons.get(0);
		when(personRepository.findByName(anyString(), anyString()))
				.thenReturn(person);
		// assert
		assertThrows(AlreadyExistsException.class,
				() -> personService.addPerson(person));

	}
	@Test
	@Tag("update")
	@DisplayName("update Person test should update a person and return person updated")
	void updatePerson_test_shouldUpdatePerson_ReturnPersonUpdated()
			throws PersonNotFoundException {
		// arrange
		Person person = new Person("John", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-8888", "jacobboyd@email.com");
		Person personToUpdate = persons.get(0);
		PersonDTO personUpdatedDTO = new PersonDTO("John", "Boyd",
				"1509 Culver st", "Culver", 97451, "841-874-8888",
				"jacobboyd@email.com");
		when(personRepository.findByName(anyString(), anyString()))
				.thenReturn(personToUpdate);
		when(personRepository.updatePerson(person)).thenReturn(person);
		when(personConverter.toPersonDTO(person)).thenReturn(personUpdatedDTO);
		// act
		PersonDTO result = personService.updatePerson(person);
		// assert
		assertThat(result).isEqualTo(personUpdatedDTO);
		verify(personRepository).updatePerson(any(Person.class));

	}
	@Test
	@Tag("update")
	@DisplayName("update Person test with an unregistered person should Throw PersonNotFoundException")
	void updatePerson_test_WithAnUnregisteredPerson_shouldThrowPersonNotFoundException()
			throws PersonNotFoundException {
		// arrange
		Person person = new Person("sofi", "Boyd", "1509 Culver st", "Culver",
				97451, "841-874-8888", "sofiboyd@email.com");
		when(personRepository.findByName(anyString(), anyString()))
				.thenReturn(null);
		// assert
		assertThrows(PersonNotFoundException.class,
				() -> personService.updatePerson(person));
		verify(personRepository).findByName(anyString(), anyString());

	}
	@Test
	@Tag("findByAddress")
	@DisplayName("find by address test should return a list of person living in the given city")
	void findByAddress_test_shouldReturnListOfperson()
			throws PersonNotFoundException {
		// arrange
		List<Person> personsByAddress = new ArrayList<Person>(
				List.of(persons.get(0), persons.get(1)));

		when(personRepository.findByAddress(anyString()))
				.thenReturn(personsByAddress);
		// // act
		List<Person> result = personService.findByAddress("1509 Culver st");
		// // assert
		assertThat(result).containsAll(personsByAddress);
		verify(personRepository).findByAddress(anyString());
	}
	@Test
	@Tag("findByAddress")
	@DisplayName("find by address test with no one in the given address should throw PersonNotFoundException ")
	void findByAddress_test_shouldthrowPersonNotFoundException()
			throws PersonNotFoundException {
		// arrange
		when(personRepository.findByAddress(anyString())).thenReturn(null);
		// // assert
		assertThrows(PersonNotFoundException.class,
				() -> personService.findByAddress(anyString()));
		verify(personRepository).findByAddress(anyString());
	}
	@Test
	@Tag("findByLastName")
	@DisplayName("find person by name test should return list of person with the given last name")
	void findByLastName_test_shouldReturnListOfperson()
			throws PersonNotFoundException {
		// arrange
		List<Person> personsBylastName = new ArrayList<Person>(
				List.of(persons.get(0), persons.get(1)));

		when(personRepository.findPersonsByLastName(anyString()))
				.thenReturn(personsBylastName);
		// // act
		List<Person> result = personService.findPersonsByLastName("Boyd");
		// // assert
		assertThat(result).containsAll(personsBylastName);
		verify(personRepository).findPersonsByLastName(anyString());
	}
	@Test
	@Tag("findByCity")
	@DisplayName("find person by city test should return a list of person living in the given city")
	void findByCity_test_shouldReturnListOfperson()
			throws PersonNotFoundException {
		// arrange
		when(personRepository.findByCity(anyString())).thenReturn(persons);
		// // act
		List<Person> result = personService.findByCity("Culver");
		// // assert
		assertThat(result).containsAll(persons);
		verify(personRepository).findByCity(anyString());
	}
	@Test
	@Tag("findByLastName")
	@DisplayName("find by Last Name test with no one with given last name should throw PersonNotFoundException ")
	void findByLastName_test_shouldthrowPersonNotFoundException()
			throws PersonNotFoundException {
		// arrange
		when(personRepository.findPersonsByLastName(anyString()))
				.thenReturn(null);
		// // assert
		assertThrows(PersonNotFoundException.class,
				() -> personService.findPersonsByLastName(anyString()));
		verify(personRepository).findPersonsByLastName(anyString());
	}
	@Test
	@Tag("findByCity")
	@DisplayName("find by city test with no one living in the given city should throw PersonNotFoundException ")
	void findByity_test_shouldthrowPersonNotFoundException()
			throws PersonNotFoundException {
		// arrange
		when(personRepository.findByCity(anyString())).thenReturn(null);
		// // assert
		assertThrows(PersonNotFoundException.class,
				() -> personService.findByCity(anyString()));
		verify(personRepository).findByCity(anyString());
	}

}
