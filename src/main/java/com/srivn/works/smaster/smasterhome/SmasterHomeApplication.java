package com.srivn.works.smaster.smasterhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class SmasterHomeApplication {

	private static final Logger logger = LoggerFactory.getLogger(SmasterHomeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SmasterHomeApplication.class, args);
		logger.info("Starting SmasterHomeApplication");
	}

}
