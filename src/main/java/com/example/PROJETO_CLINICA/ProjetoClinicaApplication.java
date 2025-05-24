package com.example.PROJETO_CLINICA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ProjetoClinicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoClinicaApplication.class, args);
	}

}
