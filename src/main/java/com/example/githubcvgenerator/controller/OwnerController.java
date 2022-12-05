package com.example.githubcvgenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.example.githubcvgenerator.model.Owner;
import com.example.githubcvgenerator.service.OwnerService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class OwnerController {
	
	@Autowired
	OwnerService ownerService;
	
	@GetMapping("/users/{login}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Owner> getOwnerByLogin(@PathVariable String login) {
		return ownerService.getOwnerByLogin(login);
	}
}
