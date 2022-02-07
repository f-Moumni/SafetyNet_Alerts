package com.safetynet.alerts.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class ReadDataFromJson implements IReadData {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ReadDataFromJson.class);
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
		LOGGER.debug("at get readData methode ");
		try {
			LOGGER.debug("reading json file ");
			dataNode = mapper.readTree(new File(jsonFilePath));
			loadPersons();
			loadFirestation();
			loadMedicalRecord();
		} catch (FileNotFoundException e) {
			LOGGER.error("ERROR fetching json file", e);
		}
	}

	private void loadPersons() {
		LOGGER.debug("loading person data ");
		JsonNode personsNode = dataNode.path("persons");

		Person person = null;
		for (JsonNode personNode : personsNode) {
			try {
				person = mapper.treeToValue(personNode, Person.class);
			} catch (JsonProcessingException | IllegalArgumentException e) {
				LOGGER.error("error converting json data to person object ", e);
			}
			personRepository.addPerson(person);
		}

	}

	private void loadFirestation() {
		LOGGER.debug("loading Fire station data ");
		JsonNode firestationsNode = dataNode.path("firestations");
		FireStation fireStation = null;
		for (JsonNode fireStationNode : firestationsNode) {
			try {
				fireStation = mapper.treeToValue(fireStationNode,
						FireStation.class);
			} catch (JsonProcessingException | IllegalArgumentException e) {
				LOGGER.error(
						"error converting json data to FireStation object ", e);
			}
			fireStationRepository.addFireStation(fireStation);
		}

	}

	private void loadMedicalRecord() {
		LOGGER.debug("loading Medical record data ");
		JsonNode medicalRecordsNode = dataNode.path("medicalrecords");
		MedicalRecord medicalRecord = null;
		for (JsonNode medicalRecordNode : medicalRecordsNode) {
			try {
				medicalRecord = mapper.treeToValue(medicalRecordNode,
						MedicalRecord.class);
			} catch (JsonProcessingException | IllegalArgumentException e) {
				LOGGER.error(
						"error converting json data to medicalrecord object ",
						e);
			}
			medicalRecordRepository.addMedicalRecord(medicalRecord);
		}

	}

}
