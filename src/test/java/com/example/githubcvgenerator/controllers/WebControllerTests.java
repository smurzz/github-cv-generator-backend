package com.example.githubcvgenerator.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebControllerTests {
	
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenUserHasNoCredentials() {
		webTestClient
		.get()
		.uri("/")
		.exchange()
		.expectStatus().is3xxRedirection();
	}
	
	@Test
	@WithMockUser
	void whenUserHasCredentials() {
		webTestClient
		.get()
		.uri("/")
		.exchange()
		.expectStatus().isOk();
	}

}

