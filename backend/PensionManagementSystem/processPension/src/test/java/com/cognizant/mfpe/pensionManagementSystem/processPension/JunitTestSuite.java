package com.cognizant.mfpe.pensionManagementSystem.processPension;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cognizant.mfpe.pensionManagementSystem.processPension.controller.ProcessPensionControllerTests;
import com.cognizant.mfpe.pensionManagementSystem.processPension.service.ProcessPensionServiceTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({ ProcessPensionControllerTests.class, ProcessPensionServiceTest.class })

public class JunitTestSuite {
}