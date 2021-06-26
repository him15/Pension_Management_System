package com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement.restClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement.model.AuthResponse;

/**
 * To access Authorization Microservice
 */
@FeignClient(name = "authorization-service", url = "http://localhost:9095")
public interface AuthClient {

	@GetMapping("/validate")
	public AuthResponse getValidity(@RequestHeader("Authorization") String token);

}
