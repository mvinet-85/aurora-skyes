package com.esiea.auroraskyesdbaccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AuroraSkyesDBAccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuroraSkyesDBAccessApplication.class, args);
	}

}
