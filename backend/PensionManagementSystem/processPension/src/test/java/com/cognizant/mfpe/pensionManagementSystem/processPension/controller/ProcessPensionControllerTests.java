package com.cognizant.mfpe.pensionManagementSystem.processPension.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cognizant.mfpe.pensionManagementSystem.processPension.model.ProcessPensionInput;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ProcessPensionControllerTests {

	@Autowired
	ProcessPensionController processpensioncontroller;

	@Autowired
	private MockMvc mvc;

	ObjectMapper objectMapper = new ObjectMapper();
	private static String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNTcyMTkxMywiaWF0IjoxNjE1NTQxOTEzfQ.sBh1dxvrhBUQWtmOIzJ0HYBIQCxZ__5Hhr1IvsOyYNI";

	@Test
	public void contextLoads() {
		assertNotNull(processpensioncontroller);
	}

	@Test
	public void getPensionDetailTestSuccess() throws Exception {
		ResultActions actions = mvc.perform(get("/PensionDetail").param("name", "Page Glasper")
				.param("dob", "27-05-1970").param("pan", "A123456789").param("aadhaar", "1234567891011120")
				.param("type", "Family pension").header("Authorization", "Bearer " + token));
		actions.andExpect(status().isOk());
	}

	@Test
	public void getPensionDetailTestFail() throws Exception {
		ResultActions actions = mvc.perform(get("/PensionDetail").param("name", "pppppp").param("dob", "27-05-1970")
				.param("pan", "A123456789").param("aadhaar", "1234567891011120").param("type", "Family pension")
				.header("Authorization", "Bearer " + token));
		actions.andExpect(status().isOk());
	}

	@Test
	public void getProcessingCodeTestSuccess() throws Exception {
		ProcessPensionInput processPensionInput = new ProcessPensionInput(1234567891011120L, 6069.98, 550);
		String jsonReq = objectMapper.writeValueAsString(processPensionInput);
		ResultActions actions = mvc.perform(post("/ProcessPension").contentType("application/json").content(jsonReq)
				.header("Authorization", "Bearer " + token));
		actions.andExpect(status().isOk());
		actions.andExpect(content().string("10"));
	}

	@Test
	public void getProcessingCodeTestFail() throws Exception {
		ProcessPensionInput processPensionInput1 = new ProcessPensionInput(7777777L, 6069.98, 58880);
		ResultActions actions = mvc.perform(post("/ProcessPension").contentType("application/json")
				.content(objectMapper.writeValueAsString(processPensionInput1))
				.header("Authorization", "Bearer " + token));
		actions.andExpect(status().isOk());
		actions.andExpect(content().string("21"));
	}

	@Test
	public void testGetProcessingCodeInvalidToken() throws Exception {
		ProcessPensionInput processPensionInput = new ProcessPensionInput(1234567891011120L, 6069.98, 550);
		ResultActions actions = mvc.perform(post("/ProcessPension").contentType("application/json")
				.content(objectMapper.writeValueAsString(processPensionInput))
				.header("Authorization", "Bearer " + "randomToken"));
		actions.andExpect(status().isOk());
		actions.andExpect(content().string("21"));
	}
}
