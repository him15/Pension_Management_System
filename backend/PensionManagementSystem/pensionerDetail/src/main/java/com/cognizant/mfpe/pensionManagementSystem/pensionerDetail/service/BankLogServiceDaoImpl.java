package com.cognizant.mfpe.pensionManagementSystem.pensionerDetail.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.UnexpectedRollbackException;

import com.cognizant.mfpe.pensionManagementSystem.pensionerDetail.dao.BankLogServiceDao;
import com.cognizant.mfpe.pensionManagementSystem.pensionerDetail.model.BankLog;
import com.cognizant.mfpe.pensionManagementSystem.pensionerDetail.repository.BankRepository;
import com.cognizant.mfpe.pensionManagementSystem.pensionerDetail.restClients.AuthClient;

@Component
public class BankLogServiceDaoImpl implements BankLogServiceDao {
	private static Logger logger = LoggerFactory.getLogger(BankLogServiceDaoImpl.class);

	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private AuthClient authClient;

	/**
	 * This method stores bank log in the database
	 * 
	 * @param BankLog
	 * @return Boolean
	 */
	@Override
	@Transactional
	public boolean logTransaction(BankLog bankLog) {
		logger.info("START");

		try {
			bankRepository.save(bankLog);
		} catch (UnexpectedRollbackException e) {
			logger.info("EXCEPTION");
			return false;
		}
		logger.info("END");

		return true;
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
