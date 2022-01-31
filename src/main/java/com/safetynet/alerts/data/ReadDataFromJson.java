package com.safetynet.alerts.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

	@Override
	@PostConstruct
	public void readData() throws IOException {
		log.info("reading JSON data from file");
		JsonNode localeTemp = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			localeTemp = mapper.readTree(new File(jsonFilePath));
		} catch (FileNotFoundException e) {
			log.error("ERROR fetching json file", e);
		}
		if (localeTemp) {

			JsonNode personsNode = localeTemp.get("persons");

			for (JsonNode jsonNode : personsNode) {
				Person person = mapper.treeToValue(jsonNode, Person.class);
				listPersons.add(person);
			}

			JsonNode firestationsNode = localeTemp.get("firestations");

			for (JsonNode jsonNode : firestationsNode) {
				FireStation fireStation = mapper.treeToValue(jsonNode,
						FireStation.class);
				listFireStations.add(fireStation);
			}
			JsonNode medicalRecordNode = localeTemp.get("medicalrecords");

			for (JsonNode jsonNode : medicalRecordNode) {
				MedicalRecord medicalRecord = mapper.treeToValue(jsonNode,
						MedicalRecord.class);
				listMedicalrecords.add(medicalRecord);
			}
		}
		for (MedicalRecord medicalRecord : listMedicalrecords) {
			System.out.println(medicalRecord);

		}

	}

}
