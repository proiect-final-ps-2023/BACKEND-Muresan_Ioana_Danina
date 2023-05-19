package com.proiectps.shopping;
import com.proiectps.shopping.service.impl.ComandaServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

	@Bean
	CommandLineRunner init()
	{
		return args -> {
 		};
	}


}
