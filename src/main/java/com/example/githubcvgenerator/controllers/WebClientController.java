package com.example.githubcvgenerator.controllers;

import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import com.example.githubcvgenerator.modell.Owner;
import com.example.githubcvgenerator.modell.Repo;
import com.example.githubcvgenerator.modell.Resume;
import com.example.githubcvgenerator.service.WebClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class WebClientController {
	
	@Autowired
	WebClientService webClientService;
	
	@GetMapping("/resume/{login}")
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({NullPointerException.class, InterruptedException.class, ExecutionException.class})
	public ResponseEntity<Mono<Resume>> getResumeByLogin(@PathVariable String login){
		try {
			Mono<Resume> resultResume = webClientService.getResumeByLogin(login);
			return new ResponseEntity<>(resultResume, HttpStatus.OK);
		} catch (NullPointerException e) {
			Mono<Resume> error = Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User with login " + login + " is found"));
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);	
		}
	}
	
	@GetMapping("/users/{login}")
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(HttpClientErrorException.class)
	public Mono<Owner> getOwnerByLogin(@PathVariable String login) {
		return webClientService.getOwnerByLogin(login);
	}
	
	@GetMapping("/users/{login}/repos")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Repo> findAllUsers(@PathVariable String login, @RequestParam Long page){
		return webClientService.getReposByOwnerLogin(login, page);
	}
 
}
