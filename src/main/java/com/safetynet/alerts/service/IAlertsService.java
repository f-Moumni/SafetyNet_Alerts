package com.safetynet.alerts.service;

import java.util.HashSet;
import java.util.List;

import com.safetynet.alerts.DTO.ChildAlertDTO;
import com.safetynet.alerts.DTO.CoveredPopulationDTO;
import com.safetynet.alerts.DTO.FireDTO;
import com.safetynet.alerts.DTO.FloodDTO;
import com.safetynet.alerts.DTO.PersonInfosDTO;

public interface IAlertsService {

	CoveredPopulationDTO getPopulationCovredByStation(int station);

	ChildAlertDTO getChildrenByAddress(String address);

	HashSet<String> getPhoneNumberByAddress(int station);

	FireDTO getInhabitantByAddress(String address);

	List<FloodDTO> getFloodsByStation(int station);

	List<PersonInfosDTO> getPersonInfosByName(String firstName,
			String lastName);

	HashSet<String> getCommunityEmail(String City);

}
