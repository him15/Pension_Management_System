package com.cognizant.mfpe.pensionManagementSystem.pensionerDetail;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cognizant.mfpe.pensionManagementSystem.pensionerDetail.controller.PensionerDetailControllerTests;
import com.cognizant.mfpe.pensionManagementSystem.pensionerDetail.service.BankLogServiceTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({ PensionerDetailControllerTests.class, BankLogServiceTests.class })

public class JunitTestSuite {
}