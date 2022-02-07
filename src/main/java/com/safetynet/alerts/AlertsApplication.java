package com.safetynet.alerts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertsApplication {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AlertsApplication.class);
	public static void main(String[] args) {
		LOGGER.debug("AlertsApplication start");
		SpringApplication.run(AlertsApplication.class, args);
	}

}
