package com.cognizant.mfpe.pensionManagementSystem.processPension.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.mfpe.pensionManagementSystem.processPension.dao.ProcessPensionServiceDao;
import com.cognizant.mfpe.pensionManagementSystem.processPension.model.Pensioner;
import com.cognizant.mfpe.pensionManagementSystem.processPension.restClients.AuthClient;
import com.cognizant.mfpe.pensionManagementSystem.processPension.restClients.PensionDisbursementClient;
import com.cognizant.mfpe.pensionManagementSystem.processPension.restClients.PensionerDetailClient;

@Service
public class ProcessPensionServiceDaoImpl implements ProcessPensionServiceDao {
	private static Logger logger = LoggerFactory.getLogger(ProcessPensionServiceDaoImpl.class);
	@Autowired
	private Pensioner pensioner;
	@Autowired
	private PensionerDetailClient pensionerDetailClient;
	@Autowired
	private AuthClient authClient;
	@Autowired
	private PensionDisbursementClient pensionDisbursementClient;

	/**
	 * This method validates pensioner details
	 * 
	 * @param String token, name, dob, pan, aadhaar number, pension type
	 * @return Boolean
	 */
	@Override
	public boolean validatePensionerDetails(String token, String name, Date dob, String pan, long aadhaarNumber,
			String pensionType) {
		logger.info("START");
		if (isSessionValid(token)) {
			pensioner = pensionerDetailClient.getPensionerDetailByAadhaar(token, aadhaarNumber);
			if (pensioner == null) {
				logger.info("END");
				return false;
			}

			if (pensioner.getName().equalsIgnoreCase(name) && pensioner.getDateOfBirth().compareTo(dob) == 0
					&& pensioner.getPanNumber().equalsIgnoreCase(pan)
					&& pensioner.getTypeOfPension().equalsIgnoreCase(pensionType)) {
				logger.info("END");
				return true;
			}
			logger.info("END");

			return false;
		}
		logger.info("END");

		return false;
	}

	/**
	 * This method calculates the pension amount
	 * 
	 * @param String token, aadhaar number
	 * @return Pensioner
	 */
	@Override
	public Pensioner calculatePension(String token, long aadhaarNumber) {
		logger.info("START");
		if (isSessionValid(token)) {

			pensioner = pensionerDetailClient.getPensionerDetailByAadhaar(token, aadhaarNumber);
			double salary = pensioner.getSalary();
			double allowance = pensioner.getAllowances();
			String typeOfPension = pensioner.getTypeOfPension();
			double pension = -1;
			if (typeOfPension.equalsIgnoreCase("Self Pension")) {
				pension = (0.8 * salary) + allowance;
			} else {
				pension = (0.5 * salary) + allowance;

			}
			pensioner.setPensionAmount(pension);
			pensioner.setBankServiceCharge(
					pensionDisbursementClient.getBankServiceCharge(token, pensioner.getBankName()));
			pensionerDetailClient.updatePensioner(token, pensioner);
			logger.info("END");

			return pensioner;
		}
		return null;
	}

	/**
	 * Thus method updates the pensioner detail to CSV file
	 * 
	 * @param String token, Pensioner
	 * @return Boolean
	 */
	@Override
	public boolean updatePensioner(String token, Pensioner pensioner) {
		logger.info("START");
		if (isSessionValid(token)) {
			logger.info("END");

			return pensionerDetailClient.updatePensioner(token, pensioner);
		}
		logger.info("END");

		return false;
	}

	/**
	 * This method validates token
	 * 
	 * @param String token
	 * @return Boolean
	 */
	@Override
	public Boolean isSessionValid(String token) {
		logger.info("START");

		try {
			authClient.getValidity(token);
		} catch (Exception e) {
			logger.info("EXCEPTION");

			return false;
		}
		logger.info("END");

		return true;
	}

}
