package com.cognizant.mfpe.pensionManagementSystem.processPension.controller;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.mfpe.pensionManagementSystem.processPension.dao.ProcessPensionServiceDao;
import com.cognizant.mfpe.pensionManagementSystem.processPension.model.Pensioner;
import com.cognizant.mfpe.pensionManagementSystem.processPension.model.ProcessPensionInput;
import com.cognizant.mfpe.pensionManagementSystem.processPension.restClients.PensionDisbursementClient;
import com.cognizant.mfpe.pensionManagementSystem.processPension.restClients.PensionerDetailClient;

import io.swagger.annotations.ApiOperation;

@RestController
public class ProcessPensionController {
	private static Logger logger = LoggerFactory.getLogger(ProcessPensionController.class);

	@Autowired
	ProcessPensionServiceDao processPensionserviceDao;
	@Autowired
	PensionDisbursementClient pensionDisbursementClient;
	@Autowired
	PensionerDetailClient pensionerDetailClient;
	double pensionAmount = 0.0d;

	/**
	 * The method at this end point calculates pension amount
	 * 
	 * @param String token, name, dob, pan, aadhaar, type
	 * @return Pensioner
	 */
	@GetMapping("/PensionDetail")
	@ApiOperation(value = "Provides the details of the pensioner", notes = "Validates the pensioner details on the basis of aadhaar number", response = Pensioner.class)
	public Pensioner getPensionDetail(@RequestHeader("Authorization") String token, @RequestParam String name,
			@RequestParam String dob, @RequestParam String pan, @RequestParam Long aadhaar, @RequestParam String type)
			throws Exception {
		try {

			logger.info("START");
			if (processPensionserviceDao.isSessionValid(token)) {

				if (processPensionserviceDao.validatePensionerDetails(token, name,
						new SimpleDateFormat("dd-MM-yyyy").parse(dob), pan, aadhaar, type)) {
					Pensioner updatedPensioner = processPensionserviceDao.calculatePension(token, aadhaar);
					logger.info("END");
					return updatedPensioner;
				} else {
					logger.info("END");

					return null;
				}
			} else {
				logger.info("END");

				return null;
			}

		} catch (Exception e) {
			logger.info("EXCEPTION");

			throw e;

		}

	}

	/**
	 * The method at this end point initiates pension disbursement
	 * 
	 * @param String token, ProcessPensionInput
	 * @return process code
	 */
	@PostMapping("/ProcessPension")
	@ApiOperation(value = "Provides the processing code", notes = "10 is for the success and 21 is for wrong calculation of pension amount")
	public int getProcessingCode(@RequestHeader("Authorization") String token,
			@RequestBody ProcessPensionInput processPensionInput) {
		logger.info("START");
		int processCode = 21;
		if (processPensionserviceDao.isSessionValid(token)) {

			try {
				for (int i = 0; i < 3; i++) {
					processCode = pensionDisbursementClient.processPension(token, processPensionInput);
					if (processCode == 10) {
						logger.info("END");

						return processCode;
					}
				}
			} catch (Exception e) {
				logger.info("EXCEPTION");

				throw e;
			}
			logger.info("END");

			return processCode;
		}
		logger.info("END");

		return processCode;
	}

}
