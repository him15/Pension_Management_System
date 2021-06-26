package com.cognizant.mfpe.pensionManagementSystem.webportal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cognizant.mfpe.pensionManagementSystem.webportal.controller.WebportalControllerTests;
import com.cognizant.mfpe.pensionManagementSystem.webportal.service.WebportalServiceTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({ WebportalControllerTests.class, WebportalServiceTests.class })

public class JunitTestSuite {
}	