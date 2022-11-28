package com.example.githubcvgenerator.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.server.UnAuthenticatedServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.githubcvgenerator.GithubCvGeneratorApplication;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@PropertySource("classpath:application.properties")
@Configuration
public class WebClientConfig {

	private static final Logger LOG = LoggerFactory.getLogger(WebClientConfig.class);

	@Bean
	public WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations,
			ServerOAuth2AuthorizedClientRepository authorizedClients) {
		ServerOAuth2AuthorizedClientExchangeFilterFunction oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
				clientRegistrations, authorizedClients);
		oauth.setDefaultOAuth2AuthorizedClient(true);
		// oauth.setDefaultClientRegistrationId("github");

		return WebClient.builder().baseUrl("https://api.github.com").filters(exchangeFilterFunctions -> {
			exchangeFilterFunctions.add(oauth);
			exchangeFilterFunctions.add(logRequest());
			exchangeFilterFunctions.add(logResponse());
		}).build();
	}

	// Logging
	private ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			LOG.info("Request {} {}", clientRequest.method(), clientRequest.url());
			LOG.info("Authorization {}", clientRequest.attribute("Authorization"));
			clientRequest.headers().forEach((name, values) -> values.forEach(value -> LOG.info("{}={}", name, value)));
			return Mono.just(clientRequest);
		});
	}

	private ExchangeFilterFunction logResponse() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			LOG.info("Response status code {}", clientResponse.statusCode());
			return Mono.just(clientResponse);
		});
	}
}
