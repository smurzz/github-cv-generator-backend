package com.example.githubcvgenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.githubcvgenerator.model.Owner;
import com.example.githubcvgenerator.model.Repo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RepoService {

	@Autowired
	private WebClient webClient;

	public Flux<Repo> getRepos(String login, Long page) {
		return webClient
				.get()
				.uri(uriBuilder -> uriBuilder
						.path("/users/{login}/repos")
						.queryParam("page", "{page}")
						.build(login, page))
				.retrieve()
				.onStatus(HttpStatus.NOT_FOUND::equals,
						clientResponse -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
								"User with login " + login + " is not found")))
				.onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("5xx")))
				.bodyToFlux(Repo.class);
	}

	public Flux<Repo> getAllReposByOwnerInParallel(Mono<Owner> ownerMono) {
		return ownerMono.flatMapMany(owner -> {
			int numOfPublicRepos = owner.getPublicRepos();
			String login = owner.getLogin();
			int numOfPages = (int) (Math.ceil(numOfPublicRepos / 30.0));
			Long[] pages = new Long[numOfPages];

			for (int page = 1; page <= numOfPages; page++) {
				pages[page - 1] = (long) page;
			}
			return Flux.fromArray(pages)
					.parallel()
					.flatMap(p -> getRepos(login, p))
					.sorted((repo1, repo2) -> repo1.getName().compareTo(repo2.getName()));
		});
	}

}
