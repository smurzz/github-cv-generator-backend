package com.example.githubcvgenerator.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.BDDMockito.*;


import com.example.githubcvgenerator.config.SecurityConfig;
import com.example.githubcvgenerator.controller.OwnerController;
import com.example.githubcvgenerator.model.Owner;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = OwnerController.class)
@Import(SecurityConfig.class)
public class OwnerControllerTests {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private OwnerController ownerController;
	
	@Test
	void nonExistentLoginAndNoCredentials() {
		webTestClient
			.get()
			.uri("/users/{login}", "loginDoesNotExist")
			.exchange()
			.expectStatus().is3xxRedirection()
			.expectBody().isEmpty();
	}
	
	@Test
	@WithMockUser
	void nonExistentLoginAndCredentials() {

		given(ownerController.getOwnerByLogin("loginDoesNotExist")).willReturn(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User with login loginDoesNotExist is not found")));
		
		webTestClient
			.get()
			.uri("/users/{login}", "loginDoesNotExist")
			.exchange()
			.expectStatus().isNotFound();
			
	}
	
	@Test
	@WithMockUser
	void existentLoginAndCredentials() {
		Owner loginexists = new Owner();
		loginexists.setCreatedAt("2021-10-10T16:02:13Z");
		loginexists.setLogin("loginexists");

		given(ownerController.getOwnerByLogin("loginexists")).willReturn(Mono.just(loginexists));

		EntityExchangeResult<Owner> ownerRes = webTestClient
				.get()
				.uri("/users/loginexists")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Owner.class)
				.returnResult();
		Owner owner = ownerRes.getResponseBody();
		assertEquals("loginexists", owner.getLogin());
		assertEquals("2021-10-10T16:02:13Z", owner.getCreatedAt());
	}
}
