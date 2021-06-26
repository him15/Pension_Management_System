package com.cognizant.mfpe.pensionManagementSystem.pensionerDetail.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.mfpe.pensionManagementSystem.pensionerDetail.dao.BankLogServiceDao;
import com.cognizant.mfpe.pensionManagementSystem.pensionerDetail.model.BankLog;
import com.cognizant.mfpe.pensionManagementSystem.pensionerDetail.repository.BankRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BankLogServiceTests {
	@Autowired
	BankLogServiceDao bankLogServiceDao;
	private static String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNTcyMTkxMywiaWF0IjoxNjE1NTQxOTEzfQ.sBh1dxvrhBUQWtmOIzJ0HYBIQCxZ__5Hhr1IvsOyYNI";

	@Mock
	BankLogServiceDaoImpl bankLogServiceDaoImpl;

	@Resource
	BankRepository bankRepository;

	@Test
	public void contextLoads() {

		assertNotNull(bankLogServiceDao);

	}

	@Test
	public void logTransactionTestSuccess() throws Exception {
		BankLog bankLog = new BankLog("Wiatt McBrier",
				new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse("16-10-1963").getTime()), "D123456789",
				40000, 1172.23, "Self pension", 1234567891011123L, "Pannier", 3102897851L, 550.00, 102342.00,
				new java.sql.Date((new Date()).getTime()));
		assertTrue(bankLogServiceDao.logTransaction(bankLog));
	}

	@Test(expected = ConstraintViolationException.class)
	public void logTransactionTestFail() throws Exception {
		BankLog bankLog = new BankLog("Kim John",
				new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse("16-10-1963").getTime()), "D123456789",
				40000, 1172.23, "Self pension", 8942899757l, null, 3102897851l, 550.00, 102342.00,
				new java.sql.Date((new Date()).getTime()));
		assertFalse(bankLogServiceDao.logTransaction(bankLog));
	}

	@Test
	public void isSessionValidTestSuccess() {
		assertTrue(bankLogServiceDao.isSessionValid("Bearer " + token));
	}

	@Test
	public void isSessionValidTestFail() {
		assertFalse(bankLogServiceDao.isSessionValid("randomToken"));
	}

	@Test
	public void logTransactionTestMockSuccess() throws Exception {
		BankLog bankLog = new BankLog("Wiatt McBrier",
				new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse("16-10-1963").getTime()), "D123456789",
				40000, 1172.23, "Self pension", 1234567891011123L, "Pannier", 3102897851L, 550.00, 102342.00,
				new java.sql.Date((new Date()).getTime()));
		Mockito.when(bankLogServiceDaoImpl.logTransaction(bankLog)).thenReturn(true);
		assertTrue(bankLogServiceDaoImpl.logTransaction(bankLog));

	}

	@Test(expected = ConstraintViolationException.class)
	public void logTransactionTestMockFail() throws Exception {

		BankLog bankLog = new BankLog("Wiatt McBrier",
				new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse("16-10-1963").getTime()), "D123456789",
				40000, 1172.23, "Self pension", 1234567891011123L, "Pannier", 3102897851L, 550.00, 102342.00,
				new java.sql.Date((new Date()).getTime()));
		Mockito.when(bankLogServiceDaoImpl.logTransaction(bankLog)).thenThrow(ConstraintViolationException.class);
		bankLogServiceDaoImpl.logTransaction(bankLog);

	}

}
