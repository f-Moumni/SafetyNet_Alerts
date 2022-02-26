package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.FireStation;
/**
 * IFireStationRepository interface manage fire station data
 * 
 * @author utilisateur1
 *
 */
public interface IFireStationRepository {
	/**
	 * findAll to get all fire stations
	 */
	public List<FireStation> findAll();

	/**
	 * addFireStation to save a given fire station
	 * 
	 * @param fireStation
	 *            to save
	 */
	public void addFireStation(FireStation fireStation);

	/**
	 * delete FireStation to delete a given fire station
	 * 
	 * @param fireStation
	 *            to delete
	 */
	public void deleteFireStation(FireStation fireStation);

	/**
	 * findByAddress to get Fire station for a given address
	 * 
	 * @param address
	 * 
	 */
	FireStation findByAddress(String address);

	/**
	 * update a given firestation
	 * 
	 * @param fireStation
	 *            to update
	 */
	FireStation updateFireStation(FireStation fireStation);

	/**
	 * findByStation to get address covered by a given station
	 * 
	 * @param station
	 */
	public List<String> findByStation(int station);

}
