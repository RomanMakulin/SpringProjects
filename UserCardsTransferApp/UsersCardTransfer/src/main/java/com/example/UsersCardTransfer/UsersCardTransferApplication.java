package com.example.UsersCardTransfer;

import com.example.UsersCardTransfer.dto.card.ActionMoneyDetails;
import com.example.UsersCardTransfer.dto.card.CardUpdateDetails;
import com.example.UsersCardTransfer.dto.card.TransferDetails;
import com.example.UsersCardTransfer.services.CardServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class UsersCardTransferApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersCardTransferApplication.class, args);
	}

}
