package com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PensionDisbursementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PensionDisbursementApplication.class, args);
	}

}
