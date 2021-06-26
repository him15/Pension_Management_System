package com.cognizant.mfpe.pensionManagementSystem.webportal.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class Pensioner {
	@NotNull(message = "Name is required")
	@Size(min = 1, max = 250)
	private String name;
	@NotNull(message = "Date of birth is required")
	private Date dateOfBirth;
	@NotNull(message = "PAN number is required")
	@Pattern(regexp = "[a-zA-Z0-9]{10}")
	private String panNumber;
	private Double salary;
	private Double allowances;
	@NotNull(message = "Pension type is required")
	private String typeOfPension;
	@NotNull(message = "Aadhaar number is required")
	@Pattern(regexp = "[0-9]{16}")
	private Long aadhaarNumber;
	private String bankName;
	private Long accountNumber;
	private Double bankServiceCharge;
	private Double pensionAmount;

	public Pensioner(String name, Date dateOfBirth, String panNumber, double salary, double allowances,
			String typeOfPension, long aadhaarNumber, String bankName, long accountNumber, double bankServiceCharge,
			double pensionAmount) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.panNumber = panNumber;
		this.salary = salary;
		this.allowances = allowances;
		this.typeOfPension = typeOfPension;
		this.aadhaarNumber = aadhaarNumber;
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.bankServiceCharge = bankServiceCharge;
		this.pensionAmount = pensionAmount;
	}

	public Pensioner() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Double getAllowances() {
		return allowances;
	}

	public void setAllowances(double allowances) {
		this.allowances = allowances;
	}

	public String getTypeOfPension() {
		return typeOfPension;
	}

	public void setTypeOfPension(String typeOfPension) {
		this.typeOfPension = typeOfPension;
	}

	public Long getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(long aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBankServiceCharge() {
		return bankServiceCharge;
	}

	public void setBankServiceCharge(double bankServiceCharge) {
		this.bankServiceCharge = bankServiceCharge;
	}

	public Double getPensionAmount() {
		return pensionAmount;
	}

	public void setPensionAmount(double pensionAmount) {
		this.pensionAmount = pensionAmount;
	}

	@Override
	public String toString() {
		return String.format(
				"PensionDetail [name=%s, dateOfBirth=%s, panNumber=%s, salary=%s, allowances=%s, typeOfPension=%s, aadhaarNumber=%s, bankName=%s, accountNumber=%s, bankServiceCharge=%s, pensionAmount=%s]",
				name, dateOfBirth, panNumber, salary, allowances, typeOfPension, aadhaarNumber, bankName, accountNumber,
				bankServiceCharge, pensionAmount);
	}

}
