package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.DTO.ChildDTO;
import com.safetynet.alerts.DTO.CoveredPopulationDTO;
import com.safetynet.alerts.DTO.FireDTO;
import com.safetynet.alerts.DTO.HomeDTO;
import com.safetynet.alerts.DTO.InhabitantDTO;
import com.safetynet.alerts.DTO.PersonAlertDTO;
import com.safetynet.alerts.DTO.PersonDTO;
import com.safetynet.alerts.DTO.PersonInfosDTO;
import com.safetynet.alerts.exceptions.FireStationNotFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.util.AgeCalculator;
/**
 *
 * AlertService class Contains methods that allow interaction between Alerts
 * service and all other services.
 *
 * @author Fatima
 *
 */
@Service
public class AlertService implements IAlertsService {

	/**
	 * Create a SLF4J/LOG4J LOGGER instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AlertService.class);

	/**
	 * IPersonService's implement class reference.
	 */
	@Autowired
	private IPersonService personService;

	/**
	 * IMedicalRecordService's implement class reference.
	 */
	@Autowired
	private IMedicalRecordService medicalRecordService;

	/**
	 * IFireStationService's implement class reference.
	 */
	@Autowired
	private IFireStationService fireStationService;

	/**
	 * the maximum age of a child
	 */
	private final int CHLID_AGE_MAX = 18;

	/**
	 * getPopulationCovredByStation get all persons covered by station number
	 * with count of adults and children number
	 * 
	 * @param station
	 *            :the station number that covers population
	 * @return CovredPopulationDTO
	 * @see CoveredPopulationDTO
	 * @throws MedicalRecordNotFoundException
	 * @throws FireStationNotFoundException
	 * @throws PersonNotFoundException
	 */
	@Override
	public CoveredPopulationDTO getPopulationCovredByStation(int station)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException {

		LOGGER.debug("get population couvred by station start ");
		LOGGER.info("get population couvred for station {} ", station);
		List<String> addresses = fireStationService.findByStation(station);
		List<PersonAlertDTO> personsCouvred = new ArrayList<>();
		int numberOfAdults = 0;
		int numberOfChildren = 0;
		for (String address : addresses) {
			personService.findByAddress(address)
					.forEach(person -> personsCouvred.add(new PersonAlertDTO(
							person.getFirstName(), person.getLastName(),
							person.getAddress(), person.getPhone())));
		}
		for (PersonAlertDTO person : personsCouvred) {
			MedicalRecord medicalRecord = medicalRecordService
					.findByName(person.getFirstName(), person.getLastName());
			if (AgeCalculator
					.calculate(medicalRecord.getBirthdate()) > CHLID_AGE_MAX) {
				numberOfAdults++;
			} else {
				numberOfChildren++;
			}
		}

		return new CoveredPopulationDTO(personsCouvred, numberOfAdults,
				numberOfChildren);
	}

	/**
	 * getChildrenByAddress get a list of children with other household members
	 * living in given address
	 * 
	 * @param address
	 * @return list of ChildDTO
	 * @see ChildDTO
	 * @throws MedicalRecordNotFoundException
	 * @throws PersonNotFoundException
	 */
	@Override
	public List<ChildDTO> getChildrenByAddress(String address)
			throws MedicalRecordNotFoundException, PersonNotFoundException {
		LOGGER.debug(" get Children By Address start ");
		LOGGER.info(" get Children for Address {} ", address);
		new ArrayList<>();
		List<Person> persons = personService.findByAddress(address);
		List<ChildDTO> childrens = new ArrayList<>();
		for (Person person : persons) {
			int age = AgeCalculator.calculate(medicalRecordService
					.findByName(person.getFirstName(), person.getLastName())
					.getBirthdate());
			if (age < CHLID_AGE_MAX) {
				childrens.add(new ChildDTO(person.getFirstName(),
						person.getLastName(), age,
						persons.stream()
								.filter(family -> (!family.equals(person)))
								.map(per -> per.getFirstName() + " "
										+ per.getLastName())
								.toList()));
			}
		}
		return childrens;
	}

	/**
	 * getPhoneNumberByStation to get phone numbers of population covered by
	 * station number
	 * 
	 * @param station
	 * @return list of phone numbers
	 * @throws MedicalRecordNotFoundException
	 * @throws FireStationNotFoundException
	 * @throws PersonNotFoundException
	 */
	@Override
	public HashSet<String> getPhoneNumberByStation(int station)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException {
		LOGGER.debug("get Phone Number By station start ");
		LOGGER.info("get Phone Number for station {} ", station);
		HashSet<String> phones = new HashSet<String>();
		phones.addAll(getPopulationCovredByStation(station).getPersonsCouverd()
				.stream().map(PersonAlertDTO::getPhone)
				.collect(Collectors.toSet()));
		return phones;

	}

	/**
	 * getInhabitantByAddress Get all the inhabitants of a given address and the
	 * fire station that covers that address
	 * 
	 * @param address
	 * @return list of inhabitants and fire station number @see FireDTO
	 * @throws MedicalRecordNotFoundException
	 * @throws FireStationNotFoundException
	 * @throws PersonNotFoundException
	 */
	@Override
	public FireDTO getInhabitantByAddress(String address)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException {
		LOGGER.debug("get Inhabitant By Address start ");
		LOGGER.info("get Inhabitant for Address {}", address);
		List<InhabitantDTO> inhabitants = new ArrayList<>();
		List<Person> persons = personService.findByAddress(address);
		int station = fireStationService.findByAddress(address).getStation();
		for (Person person : persons) {
			MedicalRecord medicalRecord = medicalRecordService
					.findByName(person.getFirstName(), person.getLastName());
			int age = AgeCalculator.calculate(medicalRecord.getBirthdate());
			inhabitants.add(new InhabitantDTO(medicalRecord.getFirstName(),
					medicalRecord.getLastName(), age, person.getPhone(),
					medicalRecord.getMedications(),
					medicalRecord.getAllergies()));
		}
		return new FireDTO(address, station, inhabitants);
	}

	/**
	 * to get all homes covered by a given station
	 * 
	 * @param station
	 * @return return list of @see HomeDTO
	 * @throws MedicalRecordNotFoundException
	 * @throws FireStationNotFoundException
	 * @throws PersonNotFoundException
	 */
	@Override
	public List<HomeDTO> getHomesByStation(int station)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException {
		LOGGER.debug("get Homes By Station Start ");
		LOGGER.info("get Homes for Station {} ", station);
		List<HomeDTO> floods = new ArrayList<HomeDTO>();
		List<String> addresses = fireStationService.findByStation(station);
		for (String address : addresses) {
			floods.add(new HomeDTO(address,
					getInhabitantByAddress(address).getInhabitants()));
		}
		return floods;
	}

	/**
	 * getPersonInfosByName get all persons with given name
	 * 
	 * @param firstName
	 * @param lastName
	 * @return list of @see PersonInfosDTO
	 *
	 * @throws MedicalRecordNotFoundException
	 * @throws PersonNotFoundException
	 */
	@Override
	public List<PersonInfosDTO> getPersonInfosByName(String firstName,
			String lastName)
			throws MedicalRecordNotFoundException, PersonNotFoundException {
		LOGGER.debug("get Person Infos By Name Start ");
		LOGGER.info("get Person Infos By Name for {} {} ", firstName, lastName);
		List<PersonInfosDTO> personsInfos = new ArrayList<>();
		List<Person> personsByLastName = personService
				.findPersonsByLastName(lastName);
		PersonDTO person = personService.findByName(firstName, lastName);

		MedicalRecord medicalRecord = medicalRecordService
				.findByName(person.getFirstName(), person.getLastName());
		int age = AgeCalculator.calculate(medicalRecord.getBirthdate());
		personsInfos.add(new PersonInfosDTO(medicalRecord.getFirstName(),
				medicalRecord.getLastName(), age, person.getEmail(),
				medicalRecord.getMedications(), medicalRecord.getAllergies()));

		for (Person pr : personsByLastName) {
			if (!pr.getFirstName().equals(person.getFirstName())) {
				MedicalRecord mr = medicalRecordService
						.findByName(pr.getFirstName(), pr.getLastName());
				int old = AgeCalculator.calculate(mr.getBirthdate());
				personsInfos.add(new PersonInfosDTO(mr.getFirstName(),
						mr.getLastName(), old, pr.getEmail(),
						mr.getMedications(), mr.getAllergies()));
			}
		}

		return personsInfos;
	}

	/**
	 * getCommunityEmail get all the e-mail addresses of a community in a given
	 * city
	 * 
	 * @param City
	 * @return list of email
	 * @throws PersonNotFoundException
	 */
	@Override
	public HashSet<String> getCommunityEmail(String city)
			throws PersonNotFoundException {
		LOGGER.debug("get Community Email start ");
		LOGGER.info(" get Community Email for {} city ", city);
		HashSet<String> emails = new HashSet<>();
		emails.addAll(personService.findByCity(city).stream()
				.map(Person::getEmail).collect(Collectors.toSet()));
		return emails;

	}
}
