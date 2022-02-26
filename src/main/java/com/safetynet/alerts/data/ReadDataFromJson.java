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
/**
 * ReadDataFromJson class allows to read data from a json file
 * 
 * @see IReadData
 * @author Fatima
 *
 */
@Service
public class ReadDataFromJson implements IReadData {
	/**
	 * a SLF4J/LOG4J LOGGER instance
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ReadDataFromJson.class);

	/**
	 * a json file path
	 */
	private String jsonFilePath;

	/**
	 * a ObjectMapper to mappe file data
	 */
	private ObjectMapper mapper = new ObjectMapper();

	/*
	 * JsonNode which stores all data
	 */
	private JsonNode dataNode = null;

	/**
	 * personRepository which stores the person data
	 */
	@Autowired
	private IPersonRepository personRepository;
	/*
	 * fireStationRepository which stores the fire station data
	 */
	@Autowired
	private IFireStationRepository fireStationRepository;
	/**
	 * medicalRecordRepository which stores the medical record data
	 */
	@Autowired
	private IMedicalRecordRepository medicalRecordRepository;
	/**
	 * ReadDataFromJson constructor
	 * 
	 * @param jsonFilePath
	 */
	public ReadDataFromJson(
			@Value("${data.jsonFilePath}") String jsonFilePath) {
		this.jsonFilePath = jsonFilePath;
	}

	/**
	 * readData method of reading data from a json file
	 * 
	 * @throws FileNotFoundException
	 *             is file not found
	 */
	@Override
	@PostConstruct
	public void readData() throws IOException {
		LOGGER.debug("reading data start ");
		try {
			LOGGER.debug("reading json file ");
			dataNode = mapper.readTree(new File(jsonFilePath));
			loadPersons();
			loadFirestation();
			loadMedicalRecord();
			LOGGER.info("SUCCESS Load Data");
		} catch (FileNotFoundException e) {
			LOGGER.error("ERROR fetching json file", e);
		}
	}
	/**
	 * loadPersons get the person data and save it in personRepository
	 * 
	 * @throws JsonProcessingException
	 * @throws IllegalArgumentException
	 */
	private void loadPersons() {
		LOGGER.debug("loading person data ");
		JsonNode personsNode = dataNode.path("persons");
		Person person = null;
		for (JsonNode personNode : personsNode) {
			try {
				LOGGER.debug("mapping json data to Person object ");
				person = mapper.treeToValue(personNode, Person.class);
				personRepository.addPerson(person);
			} catch (JsonProcessingException | IllegalArgumentException e) {
				LOGGER.error("error mapping json data to Person object ", e);
			}

		}

	}
	/**
	 * loadFirestation get the fireStation data and save it in
	 * fireStationRepository
	 * 
	 * @throws JsonProcessingException
	 * @throws IllegalArgumentException
	 */
	private void loadFirestation() {
		LOGGER.debug("loading Fire station data ");
		JsonNode firestationsNode = dataNode.path("firestations");
		FireStation fireStation = null;
		for (JsonNode fireStationNode : firestationsNode) {
			try {
				LOGGER.debug("mapping json data to FireStation object ");
				fireStation = mapper.treeToValue(fireStationNode,
						FireStation.class);
				fireStationRepository.addFireStation(fireStation);
			} catch (JsonProcessingException | IllegalArgumentException e) {
				LOGGER.error("error mapping json data to FireStation object ",
						e);
			}

		}

	}
	/**
	 * loadMedicalRecord get the medicalRecord data and save it in
	 * medicalRecordRepository
	 * 
	 * @throws JsonProcessingException
	 * @throws IllegalArgumentException
	 */
	private void loadMedicalRecord() {
		LOGGER.debug("loading Medical record data ");
		JsonNode medicalRecordsNode = dataNode.path("medicalrecords");
		MedicalRecord medicalRecord = null;
		for (JsonNode medicalRecordNode : medicalRecordsNode) {
			try {
				LOGGER.debug("mapping json data to MedicalRecord object ");
				medicalRecord = mapper.treeToValue(medicalRecordNode,
						MedicalRecord.class);
				medicalRecordRepository.addMedicalRecord(medicalRecord);
			} catch (JsonProcessingException | IllegalArgumentException e) {
				LOGGER.error("error mapping json data to medicalrecord object ",
						e);
			}

		}

	}
	/**
	 * clearData allow to clear all data in repository
	 * 
	 * @throws IOException
	 */
	public void clearData() throws IOException {
		personRepository.findAll().clear();
		fireStationRepository.findAll().clear();
		medicalRecordRepository.findAll().clear();
	}

}
