package com.example.githubcvgenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.githubcvgenerator.model.Repo;
import com.example.githubcvgenerator.service.RepoService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping
public class RepoController {
	
	@Autowired
	RepoService repoService;
	
	@GetMapping("/users/{login}/repos")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Repo> findAllRepos(@PathVariable String login, @RequestParam Long page){
		return repoService.getRepos(login, page);
	}
}
