package com.lama.dsa.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.lama.dsa.repository",
		"com.lama.dsa.controller", "com.lama.dsa.model",
		"com.lama.dsa.service","com.lama.dsa.swagger",
		"com.lama.dsa.utils"})
@EnableMongoRepositories(basePackages = {"com.lama.dsa.repository"})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
}
