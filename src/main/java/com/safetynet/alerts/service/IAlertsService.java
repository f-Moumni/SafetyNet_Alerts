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
/**
 * IAlertsService interface contain all business service methods for alerts
 * 
 * @author Fatima
 *
 */
public interface IAlertsService {
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
	public CoveredPopulationDTO getPopulationCovredByStation(int station)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException;
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
	public List<ChildDTO> getChildrenByAddress(String address)
			throws MedicalRecordNotFoundException, PersonNotFoundException;
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
	public FireDTO getInhabitantByAddress(String address)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException;
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
	public List<PersonInfosDTO> getPersonInfosByName(String firstName,
			String lastName)
			throws MedicalRecordNotFoundException, PersonNotFoundException;

	/**
	 * getCommunityEmail get all the e-mail addresses of a community in a given
	 * city
	 * 
	 * @param City
	 * @return list of email
	 * @throws PersonNotFoundException
	 */

	public HashSet<String> getCommunityEmail(String City)
			throws PersonNotFoundException;
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
	public HashSet<String> getPhoneNumberByStation(int station)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException;
	/**
	 * to get all homes covered by a given station
	 * 
	 * @param station
	 * @return return list of @see HomeDTO
	 * @throws MedicalRecordNotFoundException
	 * @throws FireStationNotFoundException
	 * @throws PersonNotFoundException
	 */
	public List<HomeDTO> getHomesByStation(int station)
			throws MedicalRecordNotFoundException, FireStationNotFoundException,
			PersonNotFoundException;

}
