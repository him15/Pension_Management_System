package com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement.dao.PensionDisbursementServiceDao;
import com.cognizant.mfpe.pensionManagementSystem.pensionDisbursement.model.ProcessPensionInput;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PensionDisbursementServiceTests {
	ProcessPensionInput processPensionInput = new ProcessPensionInput(1234567891011120L, 6069.98, 550);
	ProcessPensionInput processPensionInput1 = new ProcessPensionInput(1234567891011121L, 6069.98, 550);
	ProcessPensionInput processPensionInput2 = new ProcessPensionInput(12345678910120L, 60, 55);
	ProcessPensionInput processPensionInput3 = new ProcessPensionInput(12345678910120L, 6000, 550);
	ProcessPensionInput processPensionInput4 = new ProcessPensionInput(1234567891011120L, 6069.98, 500);
	ProcessPensionInput processPensionInput5 = new ProcessPensionInput(123456789101112000L, 6069.98, 550);
	ProcessPensionInput processPensionInput6 = new ProcessPensionInput(12345678910111L, 6069.98, 550);

	private static String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNTcyMTkxMywiaWF0IjoxNjE1NTQxOTEzfQ.sBh1dxvrhBUQWtmOIzJ0HYBIQCxZ__5Hhr1IvsOyYNI";

	@Autowired
	private PensionDisbursementServiceDao pensionDisbursementServiceDao;

	@Test
	public void contextLoads() {
		assertNotNull(pensionDisbursementServiceDao);
	}

	@Test
	public void processPensionTestSuccess() throws Exception {
		assertEquals(10, pensionDisbursementServiceDao.processPension(token, processPensionInput));

	}

	@Test
	public void processPensionTestWrongAadhaarFail() throws Exception {
		assertEquals(21, pensionDisbursementServiceDao.processPension(token, processPensionInput1));

	}

	@Test
	public void processPensionTestWrongAmountFail() throws Exception {
		assertEquals(21, pensionDisbursementServiceDao.processPension(token, processPensionInput2));

	}

	@Test
	public void processPensionTestWrongPensionFail() throws Exception {
		assertEquals(21, pensionDisbursementServiceDao.processPension(token, processPensionInput3));

	}

	@Test
	public void processPensionTestWrongServiceChargeFail() throws Exception {
		assertEquals(21, pensionDisbursementServiceDao.processPension(token, processPensionInput4));

	}

	@Test
	public void processPensionTestLongAadhaarFail() throws Exception {
		assertEquals(21, pensionDisbursementServiceDao.processPension(token, processPensionInput5));

	}

	@Test
	public void processPensionTestShortAadhaarFail() throws Exception {
		assertEquals(21, pensionDisbursementServiceDao.processPension(token, processPensionInput6));

	}

	@Test
	public void testisSessionValidTestSuccess() throws Exception {
		assertTrue(pensionDisbursementServiceDao.isSessionValid(token));
	}

	@Test
	public void testisSessionValidTestFail() throws Exception {
		assertFalse(pensionDisbursementServiceDao.isSessionValid("randomToken"));
	}

}
