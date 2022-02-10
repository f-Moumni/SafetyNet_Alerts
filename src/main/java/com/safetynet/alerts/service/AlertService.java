package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.DTO.ChildAlertDTO;
import com.safetynet.alerts.DTO.ChildDTO;
import com.safetynet.alerts.DTO.CoveredPopulationDTO;
import com.safetynet.alerts.DTO.FireDTO;
import com.safetynet.alerts.DTO.FloodDTO;
import com.safetynet.alerts.DTO.InhabitantDTO;
import com.safetynet.alerts.DTO.PersonAlertDTO;
import com.safetynet.alerts.DTO.PersonInfosDTO;
import com.safetynet.alerts.exceptions.FireStationNoteFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.util.AgeCalculator;

@Service
public class AlertService implements IAlertsService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AlertService.class);

	private final IPersonService personService;
	private final IMedicalRecordService medicalRecordService;
	private final IFireStationService fireStationService;

	private final int CHLIDREN_AGE_MAX = 18;

	@Autowired
	public AlertService(IPersonService personService,
			IMedicalRecordService medicalRecordService,
			IFireStationService fireStationService) {
		this.personService = personService;
		this.medicalRecordService = medicalRecordService;
		this.fireStationService = fireStationService;
	}

	@Override
	public CoveredPopulationDTO getPopulationCovredByStation(int station)
			throws MedicalRecordNotFoundException {
		LOGGER.debug(
				"at Alert Service in getPopulationCovredByStation methode ");
		List<String> addresses = fireStationService.FindByStation(station);
		List<PersonAlertDTO> personsCouvred = new ArrayList<>();
		int numberOfAdults = 0;
		int numberOfChildren = 0;

		addresses.forEach(address -> personService.findByAddress(address)
				.forEach(person -> personsCouvred.add(new PersonAlertDTO(
						person.getFirstName(), person.getLastName(),
						person.getAddress(), person.getPhone()))));
		for (PersonAlertDTO person : personsCouvred) {
			MedicalRecord medicalRecord = medicalRecordService
					.findByName(person.getFirstName(), person.getLastName());
			if (AgeCalculator.calculate(
					medicalRecord.getBirthdate()) > CHLIDREN_AGE_MAX) {
				numberOfAdults++;
			} else {
				numberOfChildren++;
			}
		}
		return new CoveredPopulationDTO(personsCouvred, numberOfAdults,
				numberOfChildren);
	}

	@Override
	public ChildAlertDTO getChildrenByAddress(String address)
			throws MedicalRecordNotFoundException {
		LOGGER.debug("at Alert Service in getChildrenByAddress methode ");

		List<Person> persons = personService.findByAddress(address);
		List<String> family = new ArrayList<>();
		List<ChildDTO> children = new ArrayList<>();
		for (Person person : persons) {
			int age = AgeCalculator.calculate(medicalRecordService
					.findByName(person.getFirstName(), person.getLastName())
					.getBirthdate());
			if (age < CHLIDREN_AGE_MAX) {
				children.add(new ChildDTO(person.getFirstName(),
						person.getLastName(), age));
			} else {
				family.add(person.getFirstName() + "  " + person.getLastName());
			}
		}
		return ((children.isEmpty())
				? null
				: new ChildAlertDTO(children, family));

	}

	@Override
	public HashSet<String> getPhoneNumberByStation(int station)
			throws MedicalRecordNotFoundException {
		LOGGER.debug("at Alert Service in getPhoneNumberByAddress methode ");
		HashSet<String> phones = new HashSet<String>();
		phones.addAll(getPopulationCovredByStation(station).getPersonsCouverd()
				.stream().map(PersonAlertDTO::getPhone)
				.collect(Collectors.toSet()));
		return phones;

	}

	@Override
	public FireDTO getInhabitantByAddress(String address)
			throws MedicalRecordNotFoundException,
			FireStationNoteFoundException {
		LOGGER.debug("at Alert Service in getInhabitantByAddress methode ");
		List<InhabitantDTO> inhabitants = new ArrayList<InhabitantDTO>();
		List<Person> persons = personService.findByAddress(address);
		int station = fireStationService.FindByAddress(address).getStation();
		for (Person person : persons) {
			MedicalRecord medicalRecord = medicalRecordService
					.findByName(person.getFirstName(), person.getLastName());
			int age = AgeCalculator.calculate(medicalRecord.getBirthdate());
			inhabitants.add(new InhabitantDTO(medicalRecord.getFirstName(),
					medicalRecord.getLastName(), age, address,
					medicalRecord.getMedications(),
					medicalRecord.getAllergies()));
		}
		return new FireDTO(address, station, inhabitants);
	}
	@Override
	public List<FloodDTO> getFloodsByStation(int station)
			throws MedicalRecordNotFoundException,
			FireStationNoteFoundException {
		LOGGER.debug("at Alert Service in getFloodsByStation methode ");
		List<FloodDTO> floods = new ArrayList<FloodDTO>();
		List<String> addresses = fireStationService.FindByStation(station);
		for (String address : addresses) {
			floods.add(new FloodDTO(address,
					getInhabitantByAddress(address).getInhabitants()));
		}
		return floods;

	}
	@Override

	public List<PersonInfosDTO> getPersonInfosByName(String firstName,
			String lastName)
			throws MedicalRecordNotFoundException, PersonNotFoundException {
		LOGGER.debug("at Alert Service in getPersonInfosByName methode ");
		List<PersonInfosDTO> personsInfos = new ArrayList<PersonInfosDTO>();
		List<Person> personsByLastName = personService
				.findPersonsByLastName(lastName);
		Person person = personService.findByName(firstName, lastName);

		MedicalRecord medicalRecord = medicalRecordService
				.findByName(person.getFirstName(), person.getLastName());;
		int age = AgeCalculator.calculate(medicalRecord.getBirthdate());
		personsInfos.add(new PersonInfosDTO(medicalRecord.getFirstName(),
				medicalRecord.getLastName(), age, person.getEmail(),
				medicalRecord.getMedications(), medicalRecord.getAllergies()));

		for (Person pr : personsByLastName) {
			if (!pr.getFirstName().equals(person.getFirstName())) {
				MedicalRecord mr = medicalRecordService
						.findByName(pr.getFirstName(), pr.getLastName());;
				int old = AgeCalculator.calculate(mr.getBirthdate());
				personsInfos.add(new PersonInfosDTO(mr.getFirstName(),
						mr.getLastName(), old, person.getEmail(),
						mr.getMedications(), mr.getAllergies()));
			}
		}

		return personsInfos;
	}
	@Override
	public HashSet<String> getCommunityEmail(String City) {
		LOGGER.debug("at Alert Service in getCommunityEmail methode ");
		HashSet<String> emails = new HashSet<>();
		emails.addAll(personService.findByCity(City).stream()
				.map(Person::getEmail).collect(Collectors.toSet()));
		return emails;

	}
}
