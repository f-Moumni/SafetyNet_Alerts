package com.safetynet.alerts.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Data
@Slf4j
@Service
public class ReadDataFromJson implements IReadData {

	@Value("${data.jsonFilePath}")
	private String jsonFilePath;
	private List<Person> listPersons = new ArrayList<>();
	private List<FireStation> listFireStations = new ArrayList<>();
	private List<MedicalRecord> listMedicalrecords = new ArrayList<>();
	JsonNode dataNode = null;
	ObjectMapper mapper = new ObjectMapper();
	@Override
	@PostConstruct
	public void readData() throws IOException {
		log.info("reading JSON data from file");

		try {
			dataNode = mapper.readTree(new File(jsonFilePath));
		} catch (FileNotFoundException e) {
			log.error("ERROR fetching json file", e);
		}
	}

	@Override
	public List<Person> getListPersons() {
		JsonNode personsNode = dataNode.at("/persons");
		if (!personsNode.isEmpty()) {
			Person person = null;
			for (JsonNode jsonNode : personsNode) {
				try {
					person = mapper.treeToValue(jsonNode, Person.class);
				} catch (JsonProcessingException | IllegalArgumentException e) {
					log.error("error converting json data to person object ",
							e);
				}
				listPersons.add(person);
			}
		} else {
			log.error("person note found");
			System.out.println("error");
		}
		return listPersons;

	}
	@Override
	public List<FireStation> getListFireStations() {
		JsonNode firestationsNode = dataNode.at("/firestations");
		FireStation fireStation = null;
		for (JsonNode jsonNode : firestationsNode) {
			try {
				fireStation = mapper.treeToValue(jsonNode, FireStation.class);
			} catch (JsonProcessingException | IllegalArgumentException e) {
				log.error("error converting json data to FireStations object ",
						e);
			}
			listFireStations.add(fireStation);
		}
		return listFireStations;
	}
	@Override
	public List<MedicalRecord> getListmedicalRecordNode() {
		JsonNode medicalRecordNode = dataNode.at("/medicalrecords");
		MedicalRecord medicalRecord = null;
		for (JsonNode jsonNode : medicalRecordNode) {
			try {
				medicalRecord = mapper.treeToValue(jsonNode,
						MedicalRecord.class);
			} catch (JsonProcessingException | IllegalArgumentException e) {
				log.error(
						"error converting json data to medicalrecords object ",
						e);
			}
			listMedicalrecords.add(medicalRecord);
		}
		return listMedicalrecords;
	}

}
