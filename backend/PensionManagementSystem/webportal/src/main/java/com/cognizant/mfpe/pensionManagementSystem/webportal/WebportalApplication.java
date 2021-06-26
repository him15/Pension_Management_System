package com.cognizant.mfpe.pensionManagementSystem.webportal;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WebportalApplication implements ServletContextInitializer {
	private static Logger logger = LoggerFactory.getLogger(WebportalApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WebportalApplication.class, args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		logger.info("START");
		// Setting session name
		servletContext.getSessionCookieConfig().setName("PmsSession");
		logger.info("END");

	}
}
