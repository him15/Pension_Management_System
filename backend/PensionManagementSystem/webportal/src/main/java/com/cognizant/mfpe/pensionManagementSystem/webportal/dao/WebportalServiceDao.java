package com.cognizant.mfpe.pensionManagementSystem.webportal.dao;

import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;

import com.cognizant.mfpe.pensionManagementSystem.webportal.model.UserData;

public interface WebportalServiceDao {

	public String postLogin(UserData user, HttpSession session, ModelMap warning);

	public String getPensionerDetailForm(String token);

	public Boolean isSessionValid(String token);
}
