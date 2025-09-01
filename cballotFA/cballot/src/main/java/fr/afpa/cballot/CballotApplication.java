package fr.afpa.cballot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CballotApplication {
	public static void main(String[] args) {
		SpringApplication.run(CballotApplication.class, args);
	}
}