package com.example.githubcvgenerator.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.githubcvgenerator.model.Resume;
import com.example.githubcvgenerator.service.ResumeService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class ResumeController {
	
	@Autowired
	ResumeService resumeService;
	
	@GetMapping("/resume/{login}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Resume> getResumeByLogin(@PathVariable String login) throws InterruptedException, ExecutionException{
		return resumeService.getResumeByLogin(login);
	}
}
