package com.example.githubcvgenerator.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {
	private static final Logger LOG = LoggerFactory.getLogger(WebClientConfig.class);

	@Bean
	public WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations, 
			ServerOAuth2AuthorizedClientRepository authorizedClients) {
		ServerOAuth2AuthorizedClientExchangeFilterFunction oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
				clientRegistrations, authorizedClients);
		oauth.setDefaultClientRegistrationId("github");

		return WebClient.builder()
				.baseUrl("https://api.github.com")
				.filters(exchangeFilterFunctions -> { 
			exchangeFilterFunctions.add(oauth);
			exchangeFilterFunctions.add(logRequest());
			exchangeFilterFunctions.add(logResponse());
		})
				.build();
	}

	// Logging
	private ExchangeFilterFunction logRequest() {
		return (clientRequest, next) -> {
			LOG.info("Request: {} {}", clientRequest.method(), clientRequest.url());
			LOG.info("--- Http Headers: ---");
			clientRequest.headers().forEach(this::logHeader);
			LOG.info("--- Http Cookies: ---");
			clientRequest.cookies().forEach(this::logHeader);
			return next.exchange(clientRequest);
		};
	}

	private ExchangeFilterFunction logResponse() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			LOG.info("Response: {}", clientResponse.statusCode());
			clientResponse.headers().asHttpHeaders()
					.forEach((name, values) -> values.forEach(value -> LOG.info("{}={}", name, value)));
			return Mono.just(clientResponse);
		});
	}

	private void logHeader(String name, List<String> values) {
		values.forEach(value -> LOG.info("{}={}", name, value));
	}

}
