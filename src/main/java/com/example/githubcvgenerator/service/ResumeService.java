package com.example.githubcvgenerator.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.githubcvgenerator.model.Owner;
import com.example.githubcvgenerator.model.Repo;
import com.example.githubcvgenerator.model.Resume;

import reactor.core.publisher.Mono;

@Service
public class ResumeService {

	@Autowired
	private OwnerService ownerService;
	@Autowired
	private RepoService repoService;

	public Mono<Resume> getResumeByLogin(String login) throws InterruptedException, ExecutionException {
		Mono<Owner> owner = ownerService.getOwnerByLogin(login);
		Mono<List<Repo>> repos = repoService.getAllReposByOwnerInParallel(owner).collectList();
		return Mono.zip(owner, repos).map(objects -> {
			return new Resume(objects.getT1(), objects.getT2());
		});
	}
}