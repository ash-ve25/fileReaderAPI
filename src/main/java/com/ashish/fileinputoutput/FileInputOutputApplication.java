package com.ashish.fileinputoutput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FileInputOutputApplication {

	private static Logger log = LoggerFactory.getLogger(FileInputOutputApplication.class);
	
	public static void main(String[] args) {
		log.info("Spring Boot Application start..");
		SpringApplication.run(FileInputOutputApplication.class, args);
	}

}
