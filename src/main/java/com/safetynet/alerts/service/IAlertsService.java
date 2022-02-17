package com.safetynet.alerts.service;

import java.util.HashSet;
import java.util.List;

import com.safetynet.alerts.DTO.ChildDTO;
import com.safetynet.alerts.DTO.CoveredPopulationDTO;
import com.safetynet.alerts.DTO.FireDTO;
import com.safetynet.alerts.DTO.HomeDTO;
import com.safetynet.alerts.DTO.PersonInfosDTO;
import com.safetynet.alerts.exceptions.FireStationNotFoundException;
import com.safetynet.alerts.exceptions.MedicalRecordNotFoundException;
import com.safetynet.alerts.exceptions.PersonNotFoundException;

public interface IAlertsService {

	public CoveredPopulationDTO getPopulationCovredByStation(int station)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException;

	public List<ChildDTO> getChildrenByAddress(String address)
			throws MedicalRecordNotFoundException, PersonNotFoundException;

	public FireDTO getInhabitantByAddress(String address)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException;

	public List<PersonInfosDTO> getPersonInfosByName(String firstName,
			String lastName)
			throws MedicalRecordNotFoundException, PersonNotFoundException;

	public HashSet<String> getCommunityEmail(String City)
			throws PersonNotFoundException;

	public HashSet<String> getPhoneNumberByStation(int station)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException;

	List<HomeDTO> getHomesByStation(int station)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException;

}
