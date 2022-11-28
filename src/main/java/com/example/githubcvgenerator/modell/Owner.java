
package com.example.githubcvgenerator.modell;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "login", "id", "node_id", "avatar_url", "gravatar_id", "url", "html_url", "followers_url",
		"following_url", "gists_url", "starred_url", "subscriptions_url", "organizations_url", "repos_url",
		"events_url", "received_events_url", "type", "site_admin", "name", "company", "blog", "location", "email",
		"hireable", "bio", "twitter_username", "public_repos", "public_gists", "followers", "following", "created_at",
		"updated_at" })
@Generated("jsonschema2pojo")
public class Owner {

	@JsonProperty("login")
	private String login;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("node_id")
	private String nodeId;
	@JsonProperty("avatar_url")
	private String avatarUrl;
	@JsonProperty("gravatar_id")
	private String gravatarId;
	@JsonProperty("url")
	private String url;
	@JsonProperty("html_url")
	private String htmlUrl;
	@JsonProperty("followers_url")
	private String followersUrl;
	@JsonProperty("following_url")
	private String followingUrl;
	@JsonProperty("gists_url")
	private String gistsUrl;
	@JsonProperty("starred_url")
	private String starredUrl;
	@JsonProperty("subscriptions_url")
	private String subscriptionsUrl;
	@JsonProperty("organizations_url")
	private String organizationsUrl;
	@JsonProperty("repos_url")
	private String reposUrl;
	@JsonProperty("events_url")
	private String eventsUrl;
	@JsonProperty("received_events_url")
	private String receivedEventsUrl;
	@JsonProperty("type")
	private String type;
	@JsonProperty("site_admin")
	private Boolean siteAdmin;
	@JsonProperty("name")
	private String name;
	@JsonProperty("company")
	private Object company;
	@JsonProperty("blog")
	private String blog;
	@JsonProperty("location")
	private Object location;
	@JsonProperty("email")
	private Object email;
	@JsonProperty("hireable")
	private Object hireable;
	@JsonProperty("bio")
	private String bio;
	@JsonProperty("twitter_username")
	private Object twitterUsername;
	@JsonProperty("public_repos")
	private Integer publicRepos;
	@JsonProperty("public_gists")
	private Integer publicGists;
	@JsonProperty("followers")
	private Integer followers;
	@JsonProperty("following")
	private Integer following;
	@JsonProperty("created_at")
	private String createdAt;
	@JsonProperty("updated_at")
	private String updatedAt;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Owner() {
	}

	/**
	 * 
	 * @param receivedEventsUrl
	 * @param siteAdmin
	 * @param followingUrl
	 * @param subscriptionsUrl
	 * @param bio
	 * @param login
	 * @param type
	 * @param blog
	 * @param createdAt
	 * @param followersUrl
	 * @param company
	 * @param id
	 * @param email
	 * @param updatedAt
	 * @param gistsUrl
	 * @param hireable
	 * @param avatarUrl
	 * @param organizationsUrl
	 * @param reposUrl
	 * @param htmlUrl
	 * @param url
	 * @param starredUrl
	 * @param publicGists
	 * @param twitterUsername
	 * @param gravatarId
	 * @param followers
	 * @param following
	 * @param name
	 * @param publicRepos
	 * @param location
	 * @param eventsUrl
	 * @param nodeId
	 */
	public Owner(String login, Integer id, String nodeId, String avatarUrl, String gravatarId, String url,
			String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl,
			String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl,
			String receivedEventsUrl, String type, Boolean siteAdmin, String name, Object company, String blog,
			Object location, Object email, Object hireable, String bio, Object twitterUsername, Integer publicRepos,
			Integer publicGists, Integer followers, Integer following, String createdAt, String updatedAt) {
		super();
		this.login = login;
		this.id = id;
		this.nodeId = nodeId;
		this.avatarUrl = avatarUrl;
		this.gravatarId = gravatarId;
		this.url = url;
		this.htmlUrl = htmlUrl;
		this.followersUrl = followersUrl;
		this.followingUrl = followingUrl;
		this.gistsUrl = gistsUrl;
		this.starredUrl = starredUrl;
		this.subscriptionsUrl = subscriptionsUrl;
		this.organizationsUrl = organizationsUrl;
		this.reposUrl = reposUrl;
		this.eventsUrl = eventsUrl;
		this.receivedEventsUrl = receivedEventsUrl;
		this.type = type;
		this.siteAdmin = siteAdmin;
		this.name = name;
		this.company = company;
		this.blog = blog;
		this.location = location;
		this.email = email;
		this.hireable = hireable;
		this.bio = bio;
		this.twitterUsername = twitterUsername;
		this.publicRepos = publicRepos;
		this.publicGists = publicGists;
		this.followers = followers;
		this.following = following;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@JsonProperty("login")
	public String getLogin() {
		return login;
	}

	@JsonProperty("login")
	public void setLogin(String login) {
		this.login = login;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("node_id")
	public String getNodeId() {
		return nodeId;
	}

	@JsonProperty("node_id")
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	@JsonProperty("avatar_url")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	@JsonProperty("avatar_url")
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@JsonProperty("gravatar_id")
	public String getGravatarId() {
		return gravatarId;
	}

	@JsonProperty("gravatar_id")
	public void setGravatarId(String gravatarId) {
		this.gravatarId = gravatarId;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty("html_url")
	public String getHtmlUrl() {
		return htmlUrl;
	}

	@JsonProperty("html_url")
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	@JsonProperty("followers_url")
	public String getFollowersUrl() {
		return followersUrl;
	}

	@JsonProperty("followers_url")
	public void setFollowersUrl(String followersUrl) {
		this.followersUrl = followersUrl;
	}

	@JsonProperty("following_url")
	public String getFollowingUrl() {
		return followingUrl;
	}

	@JsonProperty("following_url")
	public void setFollowingUrl(String followingUrl) {
		this.followingUrl = followingUrl;
	}

	@JsonProperty("gists_url")
	public String getGistsUrl() {
		return gistsUrl;
	}

	@JsonProperty("gists_url")
	public void setGistsUrl(String gistsUrl) {
		this.gistsUrl = gistsUrl;
	}

	@JsonProperty("starred_url")
	public String getStarredUrl() {
		return starredUrl;
	}

	@JsonProperty("starred_url")
	public void setStarredUrl(String starredUrl) {
		this.starredUrl = starredUrl;
	}

	@JsonProperty("subscriptions_url")
	public String getSubscriptionsUrl() {
		return subscriptionsUrl;
	}

	@JsonProperty("subscriptions_url")
	public void setSubscriptionsUrl(String subscriptionsUrl) {
		this.subscriptionsUrl = subscriptionsUrl;
	}

	@JsonProperty("organizations_url")
	public String getOrganizationsUrl() {
		return organizationsUrl;
	}

	@JsonProperty("organizations_url")
	public void setOrganizationsUrl(String organizationsUrl) {
		this.organizationsUrl = organizationsUrl;
	}

	@JsonProperty("repos_url")
	public String getReposUrl() {
		return reposUrl;
	}

	@JsonProperty("repos_url")
	public void setReposUrl(String reposUrl) {
		this.reposUrl = reposUrl;
	}

	@JsonProperty("events_url")
	public String getEventsUrl() {
		return eventsUrl;
	}

	@JsonProperty("events_url")
	public void setEventsUrl(String eventsUrl) {
		this.eventsUrl = eventsUrl;
	}

	@JsonProperty("received_events_url")
	public String getReceivedEventsUrl() {
		return receivedEventsUrl;
	}

	@JsonProperty("received_events_url")
	public void setReceivedEventsUrl(String receivedEventsUrl) {
		this.receivedEventsUrl = receivedEventsUrl;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("site_admin")
	public Boolean getSiteAdmin() {
		return siteAdmin;
	}

	@JsonProperty("site_admin")
	public void setSiteAdmin(Boolean siteAdmin) {
		this.siteAdmin = siteAdmin;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("company")
	public Object getCompany() {
		return company;
	}

	@JsonProperty("company")
	public void setCompany(Object company) {
		this.company = company;
	}

	@JsonProperty("blog")
	public String getBlog() {
		return blog;
	}

	@JsonProperty("blog")
	public void setBlog(String blog) {
		this.blog = blog;
	}

	@JsonProperty("location")
	public Object getLocation() {
		return location;
	}

	@JsonProperty("location")
	public void setLocation(Object location) {
		this.location = location;
	}

	@JsonProperty("email")
	public Object getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(Object email) {
		this.email = email;
	}

	@JsonProperty("hireable")
	public Object getHireable() {
		return hireable;
	}

	@JsonProperty("hireable")
	public void setHireable(Object hireable) {
		this.hireable = hireable;
	}

	@JsonProperty("bio")
	public String getBio() {
		return bio;
	}

	@JsonProperty("bio")
	public void setBio(String bio) {
		this.bio = bio;
	}

	@JsonProperty("twitter_username")
	public Object getTwitterUsername() {
		return twitterUsername;
	}

	@JsonProperty("twitter_username")
	public void setTwitterUsername(Object twitterUsername) {
		this.twitterUsername = twitterUsername;
	}

	@JsonProperty("public_repos")
	public Integer getPublicRepos() {
		return publicRepos;
	}

	@JsonProperty("public_repos")
	public void setPublicRepos(Integer publicRepos) {
		this.publicRepos = publicRepos;
	}

	@JsonProperty("public_gists")
	public Integer getPublicGists() {
		return publicGists;
	}

	@JsonProperty("public_gists")
	public void setPublicGists(Integer publicGists) {
		this.publicGists = publicGists;
	}

	@JsonProperty("followers")
	public Integer getFollowers() {
		return followers;
	}

	@JsonProperty("followers")
	public void setFollowers(Integer followers) {
		this.followers = followers;
	}

	@JsonProperty("following")
	public Integer getFollowing() {
		return following;
	}

	@JsonProperty("following")
	public void setFollowing(Integer following) {
		this.following = following;
	}

	@JsonProperty("created_at")
	public String getCreatedAt() {
		return createdAt;
	}

	@JsonProperty("created_at")
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@JsonProperty("updated_at")
	public String getUpdatedAt() {
		return updatedAt;
	}

	@JsonProperty("updated_at")
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
