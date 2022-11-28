package com.example.githubcvgenerator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.githubcvgenerator.controllers.WebClientController;
import com.example.githubcvgenerator.modell.Owner;
import com.example.githubcvgenerator.modell.Repo;
import com.example.githubcvgenerator.modell.Resume;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {
	
	@Value("${spring.security.oauth2.client.registration.github.client-id}")
	private String clientId;
	
	@Value("${spring.security.oauth2.client.registration.github.client-secret}")
	private String clientSecret;
	
	private static final Logger LOG = LoggerFactory.getLogger(WebClientService.class);

	@Autowired
	private WebClient webClient;

//	private WebClientService(WebClient.Builder builder) {
//		webClient = builder
//				.baseUrl("https://api.github.com")
//				.filters(exchangeFilterFunctions -> {
//					exchangeFilterFunctions.add(logRequest());
//					exchangeFilterFunctions.add(logResponse());
//				})
//				.build();
//	}

	// get resume from owner and repo
	public Mono<Resume> getResumeByLogin(String login) {
		try {
			Owner owner = getOwnerByLogin(login)
					.toFuture()
					.get();
			List<Repo> repos = new ArrayList<>();
			Long pages = (long) (Math.ceil(Double.valueOf(owner.getPublicRepos() / 30.0)));
			
			for (long page = 1; page <= pages; page++) {
				repos.addAll(getReposByOwnerLogin(login, page)
						.collectList()
						.toFuture()
						.get());
			}
			return Mono.just(new Resume(owner, repos));

		} catch (InterruptedException | ExecutionException e) {
			return Mono.empty();
		}
	}

	// get user-data from "api.github.com"
	public Mono<Owner> getOwnerByLogin(String login) {
		String credentials = clientId + ":" + clientSecret;
		String encodedClientData = Base64Utils.encodeToString(credentials.getBytes());
		return webClient
				.get()
				.uri("/users/{login}", login)
				// .header("Authorization", "Basic " + encodedClientData)
				// .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId("github"))
				// .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient)) @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient authorizedClient,
				.retrieve()
				.onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty()) 
				.bodyToMono(Owner.class);
	}

	// get repos-data from "api.github.com"
	public Flux<Repo> getReposByOwnerLogin(String login, Long page) {
		return webClient
				.get()
				.uri(uriBuilder -> uriBuilder.path("/users/{login}/repos")
						.queryParam("page", "{page}")
						.build(login, page))
				.retrieve()
				.bodyToFlux(Repo.class);
	}
	
//	// Logging
//	private ExchangeFilterFunction logRequest() {
//		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
//			LOG.info("Request {} {}", clientRequest.method(), clientRequest.url());
//			// LOG.info("Authorization {}", clientRequest.attribute("Authorization"));
//			clientRequest.headers().forEach((name, values) -> values.forEach(value -> LOG.info("{}={}", name, value)));
//			return Mono.just(clientRequest);
//		});
//	}
//	
//	private ExchangeFilterFunction logResponse() {
//		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
//			LOG.info("Response status code {}", clientResponse.statusCode());
//			return Mono.just(clientResponse);
//		});
//	}
}
