package com.cognizant.mfpe.pensionManagementSystem.webportal.restClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.mfpe.pensionManagementSystem.webportal.model.Pensioner;
import com.cognizant.mfpe.pensionManagementSystem.webportal.model.ProcessPensionInput;

@FeignClient(name = "process-pension-service", url = "http://localhost:9090")
public interface ProcessPensionClient {
	@GetMapping("/PensionDetail")
	public Pensioner getPensionDetail(@RequestHeader("Authorization") String token,
			@RequestParam(value="name") String name,
			@RequestParam(value="dob") String dob, @RequestParam(value="pan") String pan,
			@RequestParam(value="aadhar") Long aadhaar, @RequestParam(value="type") String type);

	@PostMapping("/ProcessPension")
	public int getProcessingCode(@RequestHeader("Authorization") String token,
			@RequestBody ProcessPensionInput processPensionInput);
}
