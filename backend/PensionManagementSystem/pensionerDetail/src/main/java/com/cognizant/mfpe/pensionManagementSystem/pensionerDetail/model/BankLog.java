package com.cognizant.mfpe.pensionManagementSystem.pensionerDetail.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "banklog")
public class BankLog {
	// Pensioner Id
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// Pensioner name
	@NotNull
	@Column(name = "name")
	private String name;

	// Pensioner Date Of Birth
	@NotNull
	@Column(name = "dateOfBirth")
	private Date dateOfBirth;

	// Pensioner PAN Number
	@NotNull
	@Column(name = "panNumber")
	private String panNumber;

	// Pensioner Salary
	@NotNull
	@Column(name = "salary")
	private double salary;

	// Pensioner allowances
	@NotNull
	@Column(name = "allowances")
	private double allowances;

	// Pensioner pension type
	@NotNull
	@Column(name = "typeOfPension")
	private String typeOfPension;

	// Pensioner aadhaar number
	@NotNull
	@Column(name = "aadhaarNumber")
	private long aadhaarNumber;

	// Pensioner bank name
	@NotNull
	@Column(name = "bankName")
	private String bankName;

	// Pensioner account number
	@NotNull
	@Column(name = "accountNumber")
	private long accountNumber;

	// Pensioner bank service charge
	@NotNull
	@Column(name = "bankServiceCharge")
	private double bankServiceCharge;

	// Pensioner pension amount
	@NotNull
	@Column(name = "pensionAmount")
	private double pensionAmount;

	// Pensioner transaction date
	@NotNull
	@Column(name = "dateOfTransaction")
	private Date dateOfTransaction;

	public BankLog(String name, Date dateOfBirth, String panNumber, double salary, double allowances,
			String typeOfPension, long aadhaarNumber, String bankName, long accountNumber, double bankServiceCharge,
			double pensionAmount, Date dateOfTransaction) {
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
		this.dateOfTransaction = dateOfTransaction;
	}

	public BankLog() {

	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getAllowances() {
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

	public long getAadhaarNumber() {
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

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBankServiceCharge() {
		return bankServiceCharge;
	}

	public void setBankServiceCharge(double bankServiceCharge) {
		this.bankServiceCharge = bankServiceCharge;
	}

	public double getPensionAmount() {
		return pensionAmount;
	}

	public void setPensionAmount(double pensionAmount) {
		this.pensionAmount = pensionAmount;
	}

	@Override
	public String toString() {
		return String.format(
				"PensionDetail [id=%s, name=%s, dateOfBirth=%s, panNumber=%s, salary=%s, allowances=%s, typeOfPension=%s, aadhaarNumber=%s, bankName=%s, accountNumber=%s, bankServiceCharge=%s, pensionAmount=%s]",
				id, name, dateOfBirth, panNumber, salary, allowances, typeOfPension, aadhaarNumber, bankName,
				accountNumber, bankServiceCharge, pensionAmount);
	}

}
