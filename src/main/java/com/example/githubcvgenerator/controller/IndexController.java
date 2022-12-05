package com.example.githubcvgenerator.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.ModelAndViewContainer;

import reactor.core.publisher.Mono;

@RestController
public class IndexController {

	@GetMapping("")
	public Mono<String> hello(Model model){

        return Mono.just("index");
   }
	
//	@GetMapping("/")
//	Mono<Void> redirect(ServerHttpResponse response) {
//	  response.setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
//	  response.getHeaders().setLocation(URI.create("http://localhost:3000"));
//	  return response.setComplete();
//	}
}
