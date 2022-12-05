package com.example.githubcvgenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.githubcvgenerator.model.Owner;

import reactor.core.publisher.Mono;

@Service
public class OwnerService {

	@Autowired
	private WebClient webClient;

	public Mono<Owner> getOwnerByLogin(String login) {
		return webClient
				.get()
				.uri("/users/{login}", login)
				.retrieve()
				.onStatus(HttpStatus.NOT_FOUND::equals,
						clientResponse -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
								"User with login " + login + " is not found")))
				.onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("5xx")))
				.bodyToMono(Owner.class);
	}
}