package com.pricing;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class PricingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricingApplication.class, args);
	}

	@Component
	class PricingIni implements CommandLineRunner {
		public void run(String... args) throws Exception{
			System.out.println("Here we go with Spring Boot");
		}
	}

}
