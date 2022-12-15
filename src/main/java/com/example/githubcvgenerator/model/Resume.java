package com.example.githubcvgenerator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Resume {

	@JsonProperty("nameOwner")
	private String nameOwner;
	@JsonProperty("loginOwner")
	private String loginOwner;
	@JsonProperty("blogOfOwner")
	private String blogOfOwner;
	@JsonProperty("dateOfCreation")
	private String dateOfCreation;
	@JsonProperty("numOfRepos")
	private Integer numOfRepos;
	@JsonProperty("followers")
	private Integer followers;
	@JsonProperty("languages")
	private HashMap<String, Double> languages;
	@JsonProperty("repoDetails")
	private HashMap<Integer, RepoDetails> repoDetails;

	/**
	 *  Default constructor
	 */

	public Resume() {

	}

	/**
	 * 
	 */
	public Resume(String nameOwner, String loginOwner, String blogOfOwner, String dateOfCreation, Integer numOfRepos,
			Integer followers, HashMap<String, Double> languages, HashMap<Integer, RepoDetails> repoDetails) {
		this.nameOwner = nameOwner;
		this.loginOwner = loginOwner;
		this.blogOfOwner = blogOfOwner;
		this.dateOfCreation = dateOfCreation;
		this.numOfRepos = numOfRepos;
		this.followers = followers;
		this.languages = languages;
		this.repoDetails = repoDetails;
	}

	/**
	 * 
	 */
	public Resume(Owner owner, List<Repo> repos) {
		this.nameOwner = owner.getName();
		this.loginOwner = owner.getLogin();
		this.blogOfOwner = owner.getBlog();
		this.dateOfCreation = owner.getCreatedAt();
		this.numOfRepos = owner.getPublicRepos();
		this.followers = owner.getFollowers();
		this.languages = languagesPerc(repos);
		this.repoDetails = reposDetails(repos);
	}
	
	// Getters 
	public String getNameOwner() {
		return nameOwner;
	}
	
	public String getLoginOwner() {
		return loginOwner;
	}
	
	public String getBlogOfOwner() {
		return blogOfOwner;
	}
	
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	
	public Integer getNumOfRepos() {
		return numOfRepos;
	}
	
	public Integer getfollowers() {
		return followers;
	}
	
	public HashMap<String, Double> getLanguages() {
		return languages;
	}
	
	public HashMap<Integer, RepoDetails>  getRepoDetails() {
		return repoDetails;
	}
	
	// Setters
	public void setNameOwner(String nameOwner) {
		this.nameOwner = nameOwner;
	}
	
	public void setLoginOwner(String loginOwner) {
		this.loginOwner = loginOwner;
	}
	
	public void setBlogOfOwner(String blogOfOwner) {
		this.blogOfOwner = blogOfOwner;
	}
	
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public void setNumOfRepos(Integer numOfRepos) {
		this.numOfRepos = numOfRepos;
	}
	
	public void setfollowers(Integer followers) {
		this.followers = followers;
	}
	
	public void setLanguages(HashMap<String, Double> languages) {
		this.languages = languages;
	}
	
	public void setRepoDetails(HashMap<Integer, RepoDetails> repoDetails) {
		this.repoDetails = repoDetails;
	}
	
	public void setOwner(Owner owner) {
		this.nameOwner = owner.getName();
		this.loginOwner = owner.getLogin();
		this.blogOfOwner = owner.getBlog();
		this.dateOfCreation = owner.getCreatedAt();
		this.numOfRepos = owner.getPublicRepos();
		this.followers = owner.getFollowers();
	}
	
	public void setRepos(List<Repo> repos) {
		this.languages = languagesPerc(repos);
		this.repoDetails = reposDetails(repos);
	}
	
	public HashMap<String, Double> languagesPerc(List<Repo> repos) {
		
		HashMap<String, Double> languages = new HashMap<String, Double>();
		double onePers = (1.0 / (double) repos.size()) * 100.0;
		for (int i = 0; i < repos.size(); i++) {
			if (repos.get(i).getLanguage() == null || repos.get(i).getLanguage().equals("")) {
				languages.put("Others", languages.containsKey("Others") ? (languages.get("Others") + onePers) : onePers);
			} else {
				languages.put(repos.get(i).getLanguage(),
						languages.containsKey(repos.get(i).getLanguage()) ? (languages.get(repos.get(i).getLanguage()) + onePers) : onePers);
			}
		} 
		return sortByValue(languages);
	}
	
	public HashMap<Integer, RepoDetails> reposDetails(List<Repo> repos) {
		HashMap<Integer, RepoDetails> reposInfos = new HashMap<Integer, RepoDetails>();		
		repos.stream().forEach(repo -> reposInfos.put(repo.getId(), new RepoDetails(repo)));
		return reposInfos;
	}
	
	public String toString() {
		return this.nameOwner + " has " + this.numOfRepos + " reposs"; 
	}
	
	private static <K, V extends Comparable<? super V>> HashMap<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());
        Collections.reverse(list);

        HashMap<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

}
