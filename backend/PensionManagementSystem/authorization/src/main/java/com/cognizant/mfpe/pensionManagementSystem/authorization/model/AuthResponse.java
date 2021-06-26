package com.cognizant.mfpe.pensionManagementSystem.authorization.model;

public class AuthResponse {
    //User Id
	private String uid;
	//User Name
	private String name;
	//Is Token Valid
	private boolean isValid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
