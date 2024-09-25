package fr.poc.kata.onepay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EntityScan("fr.poc.kata.onepay.domain")
public class PocKataOnePay {

	public static void main(String[] args) {
		SpringApplication.run(PocKataOnePay.class, args);
	}
}
