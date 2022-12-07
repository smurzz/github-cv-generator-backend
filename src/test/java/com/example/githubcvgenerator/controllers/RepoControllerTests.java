package com.example.githubcvgenerator.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.githubcvgenerator.config.SecurityConfig;
import com.example.githubcvgenerator.controller.RepoController;
import com.example.githubcvgenerator.model.Repo;

import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = RepoController.class)
@Import(SecurityConfig.class)
public class RepoControllerTests {
	
	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private RepoController repoController;
	
	@Test
	void nonExistentReposAndNoCredentials() {
		webTestClient
			.get()
			.uri(uriBuilder -> uriBuilder
					.path("/users/{login}/repos")
					.queryParam("page", 0L)
					.build("loginDoesNotExist"))
			.exchange()
			.expectStatus().is3xxRedirection()
			.expectBody().isEmpty();
	}
	
	@Test
	@WithMockUser
	void nonExistentReposAndCredentials() {

		given(repoController.findAllRepos("loginDoesNotExist", 0L)).willReturn(Flux.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User with login loginDoesNotExist is not found")));
		
		webTestClient
			.get()
			.uri(uriBuilder -> uriBuilder
					.path("/users/{login}/repos")
					.queryParam("page", 0L)
					.build("loginDoesNotExist"))
			.exchange()
			.expectStatus().isNotFound();
	}
	
	@Test
	@WithMockUser
	void existentReposAndCredentials() throws InterruptedException, ExecutionException {
		Repo loginexistsRepos = new Repo();
		loginexistsRepos.setId(426340497);
		loginexistsRepos.setHtmlUrl("https://github.com/loginexists/hotel-rank-learning");

		given(repoController.findAllRepos("loginexists", 1L)).willReturn(Flux.just(loginexistsRepos));

		FluxExchangeResult<Repo> reposRes = webTestClient
				.get()
				.uri(uriBuilder -> uriBuilder
						.path("/users/{login}/repos")
						.queryParam("page", 1L)
						.build("loginexists"))
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.returnResult(Repo.class);
		List<Repo> repos = reposRes.getResponseBody().collectList().toFuture().get();
		assertEquals(Integer.valueOf(426340497), repos.get(0).getId());
		assertEquals("https://github.com/loginexists/hotel-rank-learning", repos.get(0).getHtmlUrl());
	}

}
