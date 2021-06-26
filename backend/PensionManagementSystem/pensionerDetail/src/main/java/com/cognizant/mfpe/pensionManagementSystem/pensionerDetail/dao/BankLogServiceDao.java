package com.cognizant.mfpe.pensionManagementSystem.pensionerDetail.dao;

import javax.transaction.Transactional;

import com.cognizant.mfpe.pensionManagementSystem.pensionerDetail.model.BankLog;

public interface BankLogServiceDao {
	@Transactional
	public boolean logTransaction(BankLog bankLog);

	public Boolean isSessionValid(String token);
}
