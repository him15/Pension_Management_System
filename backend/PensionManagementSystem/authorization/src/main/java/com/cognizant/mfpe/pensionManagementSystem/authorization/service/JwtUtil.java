package com.cognizant.mfpe.pensionManagementSystem.authorization.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
	private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	// JWT secret key
	private String secretkey = "${jwt.secret}";

	public String extractUsername(String token) {
		logger.info("START");
		logger.info("END");

		return extractClaim(token, Claims::getSubject);

	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		logger.info("START");

		final Claims claims = extractAllClaims(token);
		logger.info("END");

		return claimsResolver.apply(claims);

	}

	private Claims extractAllClaims(String token) {
		logger.info("START");
		logger.info("END");

		return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();

	}

	public String generateToken(UserDetails userDetails) {
		logger.info("START");

		Map<String, Object> claims = new HashMap<>();
		logger.info("END");

		return createToken(claims, userDetails.getUsername());
	}

	/**
	 * This method generates token
	 * 
	 * @param Claims, subject
	 * @return String token
	 */
	private String createToken(Map<String, Object> claims, String subject) {
		logger.info("START");

		String compact = Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1800000))
				.signWith(SignatureAlgorithm.HS256, secretkey).compact();
		logger.info("END");

		return compact;
	}

	/**
	 * This method validates token
	 * 
	 * @param String token
	 * @return Boolean
	 */
	public Boolean validateToken(String token) {
		logger.info("START");

		try {
			Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
			logger.info("END");

			return true;
		} catch (Exception e) {
			logger.info("EXCEPTION");
			return false;
		}

	}
}