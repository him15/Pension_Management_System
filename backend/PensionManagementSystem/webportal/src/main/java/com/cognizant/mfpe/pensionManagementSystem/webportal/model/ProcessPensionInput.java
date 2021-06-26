package com.cognizant.mfpe.pensionManagementSystem.webportal.model;

public class ProcessPensionInput {
	private Long aadhaarNumber;
	private Double pensionAmount;
	private Double bankServiceCharge;

	public ProcessPensionInput(long aadhaarNumber, double pensionAmount, double bankServiceCharge) {
		super();
		this.aadhaarNumber = aadhaarNumber;
		this.pensionAmount = pensionAmount;
		this.bankServiceCharge = bankServiceCharge;
	}

	public ProcessPensionInput() {

	}

	public Long getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(long aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public Double getPensionAmount() {
		return pensionAmount;
	}

	public void setPensionAmount(double pensionAmount) {
		this.pensionAmount = pensionAmount;
	}

	public Double getBankServiceCharge() {
		return bankServiceCharge;
	}

	public void setBankServiceCharge(double bankServiceCharge) {
		this.bankServiceCharge = bankServiceCharge;
	}
}
