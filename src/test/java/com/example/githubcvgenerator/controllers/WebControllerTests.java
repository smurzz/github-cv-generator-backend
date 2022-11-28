package com.example.githubcvgenerator.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.githubcvgenerator.modell.Owner;
import com.example.githubcvgenerator.modell.Resume;
import com.example.githubcvgenerator.service.WebClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @WebFluxTest(WebClientController.class)
// @AutoConfigureWebTestClient
public class WebControllerTests {

	@MockBean
	private WebClientService webClientService;

	@Test
	void getResumeByLoginTest(@Autowired WebTestClient webClient) {
		Resume smurrr = new Resume();
		smurrr.setDateOfCreation("2021-10-29T16:39:30Z");
		smurrr.setLoginOwner("Smurrr");
		
		given(this.webClientService.getResumeByLogin("smurrr")).willReturn(Mono.just(smurrr));
		
		EntityExchangeResult<Resume> resumeResult = webClient
				.get()
				.uri("/resume/smurrr")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Resume.class)
				.returnResult();
		Resume resume = resumeResult.getResponseBody();
		assertEquals("Smurrr", resume.getLoginOwner());
	}
}
