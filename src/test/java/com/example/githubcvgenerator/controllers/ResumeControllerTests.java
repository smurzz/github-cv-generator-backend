package com.example.githubcvgenerator.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.HashMap;
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
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.githubcvgenerator.config.SecurityConfig;
import com.example.githubcvgenerator.controller.ResumeController;
import com.example.githubcvgenerator.model.RepoDetails;
import com.example.githubcvgenerator.model.Resume;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ResumeController.class)
@Import(SecurityConfig.class)
public class ResumeControllerTests {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private ResumeController resumeController;
	
	@Test
	void nonExistentResumeAndNoCredentials() {
		webTestClient
			.get()
			.uri("/resume/{login}", "loginDoesNotExist")
			.exchange()
			.expectStatus().is3xxRedirection()
			.expectBody().isEmpty();
	}
	
	@Test
	@WithMockUser
	void nonExistentLoginAndCredentials() throws InterruptedException, ExecutionException {

		given(resumeController.getResumeByLogin("loginDoesNotExist")).willReturn(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User with login loginDoesNotExist is not found")));
		
		webTestClient
			.get()
			.uri("/resume/{login}", "loginDoesNotExist")
			.exchange()
			.expectStatus().isNotFound();
			
	}
	
	@Test
	@WithMockUser
	void existentLoginAndCredentials() throws InterruptedException, ExecutionException {
		HashMap<String, Double> languages = new HashMap<>();
		languages.put("Others", 100.0);
		RepoDetails repoDetail = new RepoDetails(426340497, "hotel-rank-learning", null, "https://github.com/loginexists/hotel-rank-learning", null);
		HashMap<Integer, RepoDetails> repoDetails = new HashMap<>();
		repoDetails.put(426340497, repoDetail);
		
		Resume loginexistsResume = new Resume(null, "loginexists", "", "2021-10-10T16:02:13Z", 1, 0, languages, repoDetails);
		
		given(resumeController.getResumeByLogin("loginexists")).willReturn(Mono.just(loginexistsResume));

		EntityExchangeResult<Resume> resumeRes = webTestClient
				.get()
				.uri("/resume/loginexists")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Resume.class)
				.returnResult();
		Resume resume = resumeRes.getResponseBody();
		
		assertEquals("loginexists", resume.getLoginOwner());
		assertEquals(1, resume.getLanguages().size());
		assertEquals("hotel-rank-learning", resume.getRepoDetails().get(426340497).getRepoName());
	}
}
