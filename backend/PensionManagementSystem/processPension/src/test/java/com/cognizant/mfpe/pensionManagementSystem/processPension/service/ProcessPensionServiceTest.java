package com.cognizant.mfpe.pensionManagementSystem.processPension.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.mfpe.pensionManagementSystem.processPension.dao.ProcessPensionServiceDao;
import com.cognizant.mfpe.pensionManagementSystem.processPension.model.Pensioner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProcessPensionServiceTest {

	@Autowired
	private ProcessPensionServiceDao processPensionserviceDao;

	private static String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNTcyMTkxMywiaWF0IjoxNjE1NTQxOTEzfQ.sBh1dxvrhBUQWtmOIzJ0HYBIQCxZ__5Hhr1IvsOyYNI";

	@Test
	public void contextLoads() {
		assertNotNull(processPensionserviceDao);
	}

	@Test
	public void calculatePensionTestSuccess() throws Exception {

		assertEquals(31654.45, processPensionserviceDao.calculatePension(token, 1234567891011170L).getPensionAmount(),
				0);
	}

	@Test(expected = NullPointerException.class)
	public void calculatePensionTestAadhaarFail() throws Exception {

		assertEquals(31654.45, processPensionserviceDao.calculatePension(token, 2134567891011170L).getPensionAmount(),
				0);
	}

	@Test
	public void calculatePensionTestTokenFail() throws Exception {
		assertNull(processPensionserviceDao.calculatePension("randomToken", 1234567891011120L));
	}

	@Test
	public void validatePensionerDetailsTestSuccess() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date d1 = sdf.parse("01-02-1949");
		assertTrue(processPensionserviceDao.validatePensionerDetails(token, "Griffin Chattock", d1, "B123456789",
				1234567891011121L, "Family pension"));
	}

	@Test
	public void validatePensionerDetailsTestFail() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date yourDate = sdf.parse("01-02-1949");
		assertFalse(processPensionserviceDao.validatePensionerDetails(token, "Griffin Chattock", yourDate, "B123456789",
				55557891011120L, "Family pension"));
	}

	@Test
	public void validatePensionerDetailsTestAllFail() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date yourDate = sdf.parse("01-02-1949");
		assertFalse(processPensionserviceDao.validatePensionerDetails("randomToken", "Princi", yourDate, "B122222789",
				00000000L, "Family pension"));
	}

	@Test
	public void updatePensionerTestSuccess() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date d1 = sdf.parse("27-05-1970");
		Pensioner pensioner1 = new Pensioner("Page Glasper", d1, "A123456789", 10000, 1069.98, "Family pension",
				1234567891011120L, "Bytecard", 2626564583L, 550, 6069.98);

		assertTrue(processPensionserviceDao.updatePensioner(token, pensioner1));
	}

	@Test
	public void updatePensionerTestTokenFail() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date d1 = sdf.parse("27-05-1970");
		Pensioner pensioner2 = new Pensioner("Page Glasper", d1, "A123456789", 10000, 1069.98, "Family pension",
				1234567891011120L, "Bytecard", 2626564583L, 550, 6069.98);

		assertFalse(processPensionserviceDao.updatePensioner("randomToken", pensioner2));
	}

	@Test
	public void isSessionValidTestSuccess() throws Exception {
		assertTrue(processPensionserviceDao.isSessionValid(token));
	}

	@Test
	public void isSessionValidTestFail() throws Exception {
		assertFalse(processPensionserviceDao.isSessionValid("randomToken"));
	}
}
