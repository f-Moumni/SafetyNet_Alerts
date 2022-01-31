package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;

import lombok.Data;
@Data
@Service
public class FireStationService implements IFireStationService {
	@Autowired
	private FireStationRepository fireStationRepository;

	@Override
	public List<FireStation> findAll() {

		return fireStationRepository.findAll();
	}
}
