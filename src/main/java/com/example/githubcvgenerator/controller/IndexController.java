package com.example.githubcvgenerator.controller;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@RestController
public class IndexController {

	@SuppressWarnings("deprecation")
	@Bean
	public RouterFunction<ServerResponse> htmlRouter(
	  @Value("classpath:/static/index.html") Resource html) {
	    return route(GET(""), request
	      -> ok().contentType(MediaType.TEXT_HTML).syncBody(html)
	    );
	}
}
