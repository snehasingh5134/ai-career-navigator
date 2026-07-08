package com.snehasingh.aicareernavigator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
public class AiCareerNavigatorApplication {
	
	public static void main(String[] args) {
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("https.protocols", "TLSv1.2");
		SpringApplication.run(AiCareerNavigatorApplication.class, args);
	}

}
