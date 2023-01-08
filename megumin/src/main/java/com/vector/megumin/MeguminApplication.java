package com.vector.megumin;

import java.io.BufferedReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MeguminApplication {
	private Logger logger = LoggerFactory.getLogger(getClass());
	public static void main(String[] args) {
		SpringApplication.run(MeguminApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadMegumin(MeguminRepository meguminRepo) {
		return args->{
			
		};
	}
}
