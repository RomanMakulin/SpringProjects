package com.example.ClientAPI;

//import com.example.ClientAPI.services.Beans;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClientApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClientApiApplication.class, args);
	}

}
