package com.safetynet.alerts.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.safetynet.alerts.DTO.PersonDTO;
import com.safetynet.alerts.model.Person;

class PersonConvertorTest {

	private static PersonConverter personConverter;
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
		personConverter = new PersonConverter();
	}
	@Test
	@Tag("toPersonDTO")
	@DisplayName("toPersonDTO test should return PersonDTO of given Person")
	void toPersonDTO_Test() {
		// arrange
		Person person = persons.get(0);
		// act
		PersonDTO result = personConverter.toPersonDTO(person);
		// assert
		assertThat(result).usingRecursiveComparison()
				.isEqualTo(personsDTO.get(0));

	}
	@Test
	@Tag("toPerson")
	@DisplayName("toPerson test should return Person of given PersonDTO")
	void toPerson_Test() {
		// arrange
		PersonDTO personDTO = personsDTO.get(0);
		// act
		Person result = personConverter.toPerson(personDTO);
		// assert
		assertThat(result).usingRecursiveComparison().isEqualTo(persons.get(0));

	}

	@Test
	@Tag("toPersonDTOList")
	@DisplayName("toPersonDTOList test should return list of PersonDTO of given list Person")
	void toPersonDTOlist_Test() {
		// act
		List<PersonDTO> result = personConverter.toPersonDTOList(persons);
		// assert
		assertThat(result).usingRecursiveComparison().isEqualTo(personsDTO);

	}

}
