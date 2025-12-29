package com.example.fruitables;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.fruitables")
public class EcomerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomerceApplication.class, args);
	}

}
