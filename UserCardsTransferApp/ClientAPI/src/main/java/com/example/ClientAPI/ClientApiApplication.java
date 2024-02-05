package com.example.ClientAPI;

import com.example.ClientAPI.services.Beans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApiApplication {

	/**
	 * Инкапсулируем бины для связи с внешним Api
	 */
	@Autowired
	Beans bean;

	public static void main(String[] args) {
		SpringApplication.run(ClientApiApplication.class, args);
	}

}
