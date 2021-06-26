package com.cognizant.mfpe.pensionManagementSystem.authorization;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cognizant.mfpe.pensionManagementSystem.authorization.controller.AuthControllerTests;
import com.cognizant.mfpe.pensionManagementSystem.authorization.service.AdminDetailsServiceTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({ AuthControllerTests.class, AdminDetailsServiceTests.class })

public class JunitTestSuite {
}