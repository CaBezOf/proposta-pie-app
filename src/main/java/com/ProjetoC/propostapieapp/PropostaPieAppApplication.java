package com.ProjetoC.propostapieapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PropostaPieAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaPieAppApplication.class, args);

	}

}
