package com.pierre.hamy.micrometer.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableMetrics
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
