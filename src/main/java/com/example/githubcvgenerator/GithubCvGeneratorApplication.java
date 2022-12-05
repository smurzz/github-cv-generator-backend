package com.example.githubcvgenerator;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class GithubCvGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubCvGeneratorApplication.class, args);
	}
	
	@Bean
	public RouterFunction<ServerResponse> htmlRouter(
	  @Value("classpath:/static/index.html") Resource html) {
	    return route(GET(""), request
	      -> ok().contentType(MediaType.TEXT_HTML).syncBody(html)
	    );
	}
	
}
