package com.trinitarias.gestorcromos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //It tells Spring: Start the app here, and scan everything inside this package.
public class GestorcromosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorcromosApplication.class, args);
	}

}
