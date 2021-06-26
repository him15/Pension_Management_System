package com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement.dao;

import com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement.model.ProcessPensionInput;

public interface PensionDisbursementServiceDao {

	public double getBankServiceCharge(String bankName);

	public int processPension(String token, ProcessPensionInput processPensionInput);

	public Boolean isSessionValid(String token);
}
