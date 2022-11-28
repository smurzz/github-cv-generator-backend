package com.example.githubcvgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.githubcvgenerator.service.WebClientService;

import reactor.core.publisher.Mono;

@PropertySource("classpath:application.properties")
@SpringBootApplication
public class GithubCvGeneratorApplication {
	
	// private static final Logger LOG = LoggerFactory.getLogger(WebClientService.class);
	
	@Value("${spring.security.oauth2.client.registration.github.client-id}")
	private String clientId;

	public static void main(String[] args) {
		SpringApplication.run(GithubCvGeneratorApplication.class, args);
	}
	
}
