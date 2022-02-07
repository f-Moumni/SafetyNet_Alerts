package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.IFireStationRepository;
import com.safetynet.alerts.repository.IMedicalRecordRepository;
import com.safetynet.alerts.repository.IPersonRepository;
import com.safetynet.alerts.util.AgeCalculator;

@Service
public class AlerstService implements IAlertsService {

	private final IPersonRepository personRepository;

	private final IMedicalRecordRepository medicalRecordRepository;

	private final IFireStationRepository fireStationRepository;

	private final int CHLIDREN_AGE_MAX = 18;

	@Autowired
	public AlerstService(IPersonRepository personRepository,
			IMedicalRecordRepository medicalRecordRepository,
			IFireStationRepository fireStationRepository) {

		this.personRepository = personRepository;
		this.medicalRecordRepository = medicalRecordRepository;
		this.fireStationRepository = fireStationRepository;
	}
	@Override
	public CoveredPopulationDTO getPopulationCovredByStation(int station) {

		List<String> addresses = fireStationRepository.FindByStation(station);
		List<PersonAlertDTO> Personscouvred = new ArrayList<>();
		int numberoOfAddulte = 0;
		int numberOfchildren = 0;

		for (String address : addresses) {
			List<Person> persons = personRepository.findByAddress(address);
			for (Person person : persons) {
				PersonAlertDTO pCouverd = new PersonAlertDTO();
				pCouverd.setAddress(address);
				pCouverd.setFirstName(person.getFirstName());
				pCouverd.setLastName(person.getLastName());
				pCouverd.setPhone(person.getPhone());
				Personscouvred.add(pCouverd);
				MedicalRecord medicalRecord = medicalRecordRepository
						.findByName(person.getFirstName(),
								person.getLastName());
				if (medicalRecord != null) {
					int age = (int) AgeCalculator
							.calculate(medicalRecord.getBirthdate());
					if (age > CHLIDREN_AGE_MAX) {
						numberoOfAddulte++;
					} else {
						numberOfchildren++;
					}
				}
			}
		}
		return new CoveredPopulationDTO(Personscouvred, numberoOfAddulte,
				numberOfchildren);
	}

	@Override
	public ChildAlertDTO getChildrenByAddress(String address) {
		ChildAlertDTO childrenAlert;
		List<Person> persons = personRepository.findByAddress(address);
		List<String> family = new ArrayList<String>();
		List<ChildDTO> children = new ArrayList<>();
		for (Person person : persons) {
			// List<PersonAlertDTO> PersonsInAddress = new
			// ArrayList<PersonAlertDTO>();
			ChildDTO childDTO = new ChildDTO();
			MedicalRecord medicalRecord = medicalRecordRepository
					.findByName(person.getFirstName(), person.getLastName());
			if (medicalRecord != null) {
				int age = (int) AgeCalculator
						.calculate(medicalRecord.getBirthdate());
				if (age < CHLIDREN_AGE_MAX) {
					childDTO.setAge(age);
					childDTO.setFirstName(person.getFirstName());
					childDTO.setLastName(person.getLastName());
					children.add(childDTO);
				} else {
					family.add(person.getFirstName() + "  "
							+ person.getLastName());
				}
			}

		}
		if (children.isEmpty()) {
			childrenAlert = new ChildAlertDTO();
		} else {
			childrenAlert = new ChildAlertDTO(children, family);
		}
		return childrenAlert;

	}

	@Override
	public HashSet<String> getPhoneNumberByAddress(int station) {
		List<PersonAlertDTO> PersonsCouverd = new ArrayList<PersonAlertDTO>();
		HashSet<String> phoneNumbers = new HashSet<String>();
		CoveredPopulationDTO covredPopulation = getPopulationCovredByStation(
				station);
		PersonsCouverd = covredPopulation.getPersonsCouverd();
		for (PersonAlertDTO person : PersonsCouverd) {
			phoneNumbers.add(person.getPhone());
		}
		return phoneNumbers;

	}

	@Override
	public FireDTO getInhabitantByAddress(String address) {
		List<InhabitantDTO> Inhabitants = new ArrayList<InhabitantDTO>();
		List<Person> persons = personRepository.findByAddress(address);
		int station = fireStationRepository.FindByAddress(address).getStation();
		for (Person person : persons) {
			MedicalRecord medicalRecord = medicalRecordRepository
					.findByName(person.getFirstName(), person.getLastName());
			int age = (int) AgeCalculator
					.calculate(medicalRecord.getBirthdate());
			Inhabitants.add(new InhabitantDTO(medicalRecord.getFirstName(),
					medicalRecord.getLastName(), age, address,
					medicalRecord.getMedications(),
					medicalRecord.getAllergies()));
		}
		return new FireDTO(address, station, Inhabitants);
	}
	@Override
	public List<FloodDTO> getFloodsByStation(int station) {
		List<FloodDTO> floods = new ArrayList<FloodDTO>();

		List<String> addresses = fireStationRepository.FindByStation(station);
		for (String address : addresses) {
			FireDTO Inhabitants = getInhabitantByAddress(address);
			FloodDTO flood = new FloodDTO(address,
					Inhabitants.getInhabitants());
			floods.add(flood);
		}

		return floods;

	}
	@Override

	public List<PersonInfosDTO> getPersonInfosByName(String firstName,
			String lastName) {
		List<PersonInfosDTO> personsInfos = new ArrayList<PersonInfosDTO>();
		List<Person> personsByAddress = personRepository
				.findPersonsByLastName(lastName);
		Person person = personRepository.findByName(firstName, lastName);

		MedicalRecord medicalRecord = medicalRecordRepository
				.findByName(person.getFirstName(), person.getLastName());;
		int age = (int) AgeCalculator.calculate(medicalRecord.getBirthdate());
		personsInfos.add(new PersonInfosDTO(medicalRecord.getFirstName(),
				medicalRecord.getLastName(), age, person.getEmail(),
				medicalRecord.getMedications(), medicalRecord.getAllergies()));

		for (Person pr : personsByAddress) {
			if (!pr.getFirstName().equals(person.getFirstName())) {
				MedicalRecord mr = medicalRecordRepository
						.findByName(pr.getFirstName(), pr.getLastName());;
				int old = (int) AgeCalculator.calculate(mr.getBirthdate());
				personsInfos.add(new PersonInfosDTO(mr.getFirstName(),
						mr.getLastName(), old, person.getEmail(),
						mr.getMedications(), mr.getAllergies()));
			}
		}

		return personsInfos;
	}
	@Override
	public HashSet<String> getCommunityEmail(String City) {
		HashSet<String> emails = new HashSet<String>();
		List<Person> personsByCity = personRepository.findByCity(City);
		emails.add(personsByCity.get(0).getEmail());
		for (Person person : personsByCity) {
			emails.add(person.getEmail());

		}
		return emails;

	}
}
