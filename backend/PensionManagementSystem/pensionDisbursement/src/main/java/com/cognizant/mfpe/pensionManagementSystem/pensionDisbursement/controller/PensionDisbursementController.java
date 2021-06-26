package com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement.dao.PensionDisbursementServiceDao;
import com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement.model.ProcessPensionInput;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class PensionDisbursementController {
	private static Logger logger = LoggerFactory.getLogger(PensionDisbursementController.class);
	@Autowired
	private PensionDisbursementServiceDao pensionDisbursementServiceDao;
	/**
	 * The method at this end point initiates pension disbursement
	 * 
	 * @param String token, ProcessPensionInput
	 * @return process code
	 */
	@PostMapping("/DisbursePension")
	@ApiOperation(value="Provides the processing code",
					notes="10 - successful calculation of pension amount , 21 - wrong calculation of pension amount")
	public int processPension(@RequestHeader("Authorization") String token,
			@RequestBody ProcessPensionInput processPensionInput) {
		logger.info("START");
		int processCode = 21;
		if (pensionDisbursementServiceDao.isSessionValid(token)) {
			processCode = pensionDisbursementServiceDao.processPension(token, processPensionInput);
			logger.info("END");
			return processCode;
		}
		logger.info("END - Token invalid");

		return processCode;

	}

	@PostMapping("/getServiceCharge")
	@ApiOperation(value="Provides the bank service charge")
	public double getBankServiceCharge(@RequestHeader("Authorization") String token,
			@RequestBody String bankName) {
		logger.info("START");

		if (pensionDisbursementServiceDao.isSessionValid(token)) {
			logger.info("END");

			return pensionDisbursementServiceDao.getBankServiceCharge(bankName);
		}
		logger.info("END - Token invalid");

		return -1;
	}
}
