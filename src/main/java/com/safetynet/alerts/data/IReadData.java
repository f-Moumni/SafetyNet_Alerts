package com.safetynet.alerts.data;

import java.io.IOException;
import java.util.List;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

public interface IReadData {

	void readData() throws IOException;
	public List<Person> getListPersons();
	List<FireStation> getListFireStations();
	List<MedicalRecord> getListmedicalRecordNode();

}
