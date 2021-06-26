package com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement.restClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement.model.Pensioner;

/**
 * To access Pensioner detail Microservice
 */
@FeignClient(name = "pensioner-detail-service", url = "http://localhost:9092")
public interface PensionerDetailClient {

	@GetMapping("/PensionerDetailByAadhaar/{aadhaarNumber}")
	public Pensioner getPensionerDetailByAadhaar(@RequestHeader("Authorization") String token,
			@PathVariable(value = "aadhaarNumber")long aadhaarNumber);

	@PostMapping("/UpdatePensioner")
	public boolean updatePensioner(@RequestHeader("Authorization") String token,
			@RequestBody Pensioner updatedPensioner);

	@PostMapping("/LogTransaction")
	public boolean logTransaction(@RequestHeader("Authorization") String token, @RequestBody Pensioner pensioner);

}
