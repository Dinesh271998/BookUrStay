package com.practice.BookUrStay;

import com.practice.BookUrStay.Exceptions.ResourceNotFoundException;
import com.practice.BookUrStay.Model.User;
import com.practice.BookUrStay.Repository.UserRepository;
import com.practice.BookUrStay.Service.JwtTokenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
class BookUrStayApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenServiceImpl jwtTokenServiceImpl;



	@Test
	void contextLoads() {

		String email = "eve@example.com";

		Optional<User> userOptional = userRepository.findByEmail(email);
		if (userOptional.isPresent()) {
			log.info("User found: {}", email);

			String token = jwtTokenServiceImpl.generateToken(userOptional.get());
			System.out.println("Generated JWT Token: " + token);

			String getSubject = jwtTokenServiceImpl.extractUsername(token);
			System.out.println("Extracted Subject: " + getSubject);
		} else {
			log.error("User not found: {}", email);
			throw new ResourceNotFoundException("User not found with email: " + email);
	}
	}
}
