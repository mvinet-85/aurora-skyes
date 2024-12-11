package com.esiea.auroraskyesdbaccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AuroraSkyesBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuroraSkyesBackApplication.class, args);
	}

}