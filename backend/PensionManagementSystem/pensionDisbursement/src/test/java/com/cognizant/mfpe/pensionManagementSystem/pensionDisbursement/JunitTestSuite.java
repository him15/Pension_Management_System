package com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement.controller.PensionDisbursementControllerTests;
import com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement.service.PensionDisbursementServiceTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({ PensionDisbursementControllerTests.class, PensionDisbursementServiceTests.class })

public class JunitTestSuite {
}