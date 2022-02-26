package com.safetynet.alerts.service;

import java.util.List;

import com.safetynet.alerts.DTO.FireStationDTO;
import com.safetynet.alerts.exceptions.AlreadyExistsException;
import com.safetynet.alerts.exceptions.DataNotFoundException;
import com.safetynet.alerts.exceptions.FireStationNotFoundException;
import com.safetynet.alerts.model.FireStation;
/**
 * IFireStationService interface for business processing of fire station CRUD
 * operations
 * 
 * @author fatima
 *
 */
public interface IFireStationService {
	/**
	 * to get all fire stations in data
	 * 
	 * @return list of fireStation
	 * @throws DataNotFoundException
	 */
	public List<FireStationDTO> findAll() throws DataNotFoundException;
	/**
	 * addFireStation save a given fire station
	 * 
	 * @param fireStation
	 *            to save
	 * @return fire station saved
	 * @throws AlreadyExistsException
	 */
	public FireStationDTO addFireStation(FireStation fireStation)
			throws AlreadyExistsException;
	/**
	 * updateFireStation update a given fire station
	 * 
	 * @param fireStation
	 *            to update
	 * @return fireStation updated
	 * @throws FireStationNotFoundException
	 */
	public FireStationDTO updateFireStation(FireStation fireStation)
			throws FireStationNotFoundException;
	/**
	 * deleteFireStation delete a fire station at given address
	 * 
	 * @param address
	 *            covered by fire station
	 * @return fire station deleted
	 * @throws FireStationNotFoundException
	 */
	public FireStationDTO deleteFireStation(String address)
			throws FireStationNotFoundException;
	/**
	 * findByStation get all addresses covered by given firStation number
	 * 
	 * @param station
	 *            fire station number
	 * @return list of address
	 * @throws FireStationNotFoundException
	 */
	public List<String> findByStation(int station)
			throws FireStationNotFoundException;
	/**
	 * findByAddress get a fireStation that covers a given address
	 * 
	 * @param address
	 * @return fireStation covered
	 * @throws FireStationNotFoundException
	 */
	public FireStation findByAddress(String address)
			throws FireStationNotFoundException;

}
