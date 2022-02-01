package com.safetynet.alerts.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.IFireStationRepository;
import com.safetynet.alerts.repository.IMedicalRecordRepository;
import com.safetynet.alerts.repository.IPersonRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Data
@Slf4j
@Service
public class ReadDataFromJson implements IReadData {

	@Value("${data.jsonFilePath}")
	private String jsonFilePath;

	JsonNode dataNode = null;
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private IPersonRepository personRepository;
	@Autowired
	private IFireStationRepository fireStationRepository;
	@Autowired
	private IMedicalRecordRepository medicalRecordRepository;

	@Override
	@PostConstruct
	public void readData() throws IOException {
		log.info("reading JSON data from file");

		try {

			dataNode = mapper.readTree(new File(jsonFilePath));
			loadPersons();
			loadFirestation();
			loadMedicalRecord();
		} catch (FileNotFoundException e) {
			log.error("ERROR fetching json file", e);
		}
	}

	private void loadPersons() {
		JsonNode personsNode = dataNode.path("persons");
		if (!personsNode.isEmpty()) {
			Person person = null;
			for (JsonNode personNode : personsNode) {
				try {
					person = mapper.treeToValue(personNode, Person.class);
				} catch (JsonProcessingException | IllegalArgumentException e) {
					log.error("error converting json data to person object ",
							e);
				}

				personRepository.addPerson(person);
			}
		} else {
			log.error("person note found");
			System.out.println("error");
		}

	}

	private void loadFirestation() {
		JsonNode firestationsNode = dataNode.path("firestations");
		FireStation fireStation = null;
		for (JsonNode fireStationNode : firestationsNode) {
			try {
				fireStation = mapper.treeToValue(fireStationNode,
						FireStation.class);
			} catch (JsonProcessingException | IllegalArgumentException e) {
				log.error("error converting json data to FireStations object ",
						e);
			}
			fireStationRepository.addFireStation(fireStation);
		}

	}

	private void loadMedicalRecord() {
		JsonNode medicalRecordsNode = dataNode.path("medicalrecords");
		MedicalRecord medicalRecord = null;
		for (JsonNode medicalRecordNode : medicalRecordsNode) {
			try {

				medicalRecord = mapper.treeToValue(medicalRecordNode,
						MedicalRecord.class);
			} catch (JsonProcessingException | IllegalArgumentException e) {
				log.error(
						"error converting json data to medicalrecords object ",
						e);
			}
			medicalRecordRepository.addMedicalRecord(medicalRecord);
		}

	}

}
