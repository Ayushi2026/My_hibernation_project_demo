package com.example.hibernation_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class HibernationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernationDemoApplication.class, args);
	}

}
