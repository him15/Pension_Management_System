package com.cognizant.mfpe.pensionManagementSystem.authorization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.mfpe.pensionManagementSystem.authorization.model.AuthResponse;
import com.cognizant.mfpe.pensionManagementSystem.authorization.model.UserData;
import com.cognizant.mfpe.pensionManagementSystem.authorization.service.AdminDetailsService;
import com.cognizant.mfpe.pensionManagementSystem.authorization.service.JwtUtil;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AuthController {
	private static Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private JwtUtil jwtutil;
	@Autowired
	private AdminDetailsService adminDetailService;

	/**
	 * The method at this end point generates token for valid login credentials
	 * 
	 * @param UserData
	 * @return AuthResponse, HttpStatus
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserData userlogincredentials) {
		logger.info("START");
		final UserDetails userdetails = adminDetailService.loadUserByUsername(userlogincredentials.getUserid());
		String uid = "";
		String generateToken = "";
		if (userdetails.getPassword().equals(userlogincredentials.getUpassword())) {
			uid = userlogincredentials.getUserid();
			generateToken = jwtutil.generateToken(userdetails);
			logger.info(generateToken);
			logger.info("END");
			return new ResponseEntity<>(new UserData(uid, null, null, generateToken), HttpStatus.OK);
		} else {
			logger.info("END - Wrong credentials");
			return new ResponseEntity<>("Not Accesible", HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * The method at this end point validates token
	 * 
	 * @param String token
	 * @return AuthResponse, HttpStatus
	 */
	@GetMapping("/validate")
	public ResponseEntity<?> getValidity(@RequestHeader("Authorization") String token) {
		logger.info("START");

		AuthResponse res = new AuthResponse();
		if (token == null) {
			res.setValid(false);
			logger.info("END - Null Token");

			return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
		} else {
			String token1 = token.substring(7);
			if (jwtutil.validateToken(token1)) {
				res.setUid(jwtutil.extractUsername(token1));
				res.setValid(true);
				res.setName("admin");
			} else {
				res.setValid(false);
				logger.info("END - Token expired");

				return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);

			}
		}
		logger.info("END - Token accepted");

		return new ResponseEntity<>(res, HttpStatus.OK);

	}

}
