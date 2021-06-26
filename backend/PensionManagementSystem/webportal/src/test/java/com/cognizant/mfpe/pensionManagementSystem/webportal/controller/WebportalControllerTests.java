package com.cognizant.mfpe.pensionManagementSystem.webportal.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.ui.ModelMap;

import com.cognizant.mfpe.pensionManagementSystem.webportal.model.Pensioner;
import com.cognizant.mfpe.pensionManagementSystem.webportal.model.ProcessPensionInput;
import com.cognizant.mfpe.pensionManagementSystem.webportal.model.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class WebportalControllerTests {
	private static String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNTcyMTkxMywiaWF0IjoxNjE1NTQxOTEzfQ.sBh1dxvrhBUQWtmOIzJ0HYBIQCxZ__5Hhr1IvsOyYNI";
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebportalController webportalController;

	@Test
	public void contextLoads() {

		assertNotNull(webportalController);

	}

	@Test
	public void getLoginPageTest() throws Exception {

		ResultActions actions = mockMvc.perform(get("/"));
		actions.andExpect(status().isOk());
	}

	@Test
	public void loginTestSuccess() throws Exception {
		UserData userData = new UserData("admin", "admin", "admin", "admin");

		ResultActions actions = mockMvc
				.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userData)));
		actions.andExpect(status().isOk());

	}

	@Test
	public void loginTestFail() throws Exception {
		UserData userData = new UserData("randomUser", "randomUser", "randomUser", "randomUser");

		ResultActions actions = mockMvc
				.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userData)));
		actions.andExpect(status().isOk());
		actions.andExpect(forwardedUrl("/WEB-INF/jsp/login.jsp"));

	}

	@Test
	public void loginTestMethodFail() throws Exception {
		UserData userData = new UserData("admin", "admin", "admin", "admin");

		ResultActions actions = mockMvc
				.perform(get("/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userData)));
		actions.andExpect(status().isMethodNotAllowed());

	}

	@Test
	public void logoutTest() throws Exception {
		MockHttpSession session = new MockHttpSession();

		ResultActions actions = mockMvc.perform(get("/logout").requestAttr("session", session));
		actions.andExpect(status().isOk());
		actions.andExpect(forwardedUrl("/WEB-INF/jsp/login.jsp"));

	}

	@Test
	public void getProcessPensionFormTest() throws Exception {
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("token", token);
		ResultActions actions = mockMvc.perform(get("/processPensionForm").requestAttr("session", session)
				.sessionAttr("processPensionInput", new ProcessPensionInput()).requestAttr("model", new ModelMap()));
		actions.andExpect(status().isOk());

	}

	@Test
	public void getPensionerDetailFormTest() throws Exception {
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("token", token);
		ResultActions actions = mockMvc.perform(get("/getPensionDetail").requestAttr("session", session)
				.sessionAttr("pensioner", new Pensioner()).requestAttr("model", new ModelMap()));
		actions.andExpect(status().isOk());

	}

	@Test
	public void postProcessPensionTestSuccess() throws Exception {
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("token", token);
		ResultActions actions = mockMvc.perform(post("/postProcessPension").requestAttr("session", session)
				.sessionAttr("processPensionInput", new ProcessPensionInput()).requestAttr("model", new ModelMap()));
		actions.andExpect(status().isOk());

	}

	@Test
	public void postProcessPensionTestFail() throws Exception {
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("token", token);
		ResultActions actions = mockMvc.perform(get("/postProcessPension").requestAttr("session", session)
				.sessionAttr("processPensionInput", new ProcessPensionInput()).requestAttr("model", new ModelMap()));
		actions.andExpect(status().isMethodNotAllowed());

	}

	public static String asJsonString(UserData admin) {
		try {
			return new ObjectMapper().writeValueAsString(admin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}