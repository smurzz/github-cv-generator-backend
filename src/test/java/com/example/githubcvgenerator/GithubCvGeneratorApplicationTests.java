package com.example.githubcvgenerator;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class GithubCvGeneratorApplicationTests {
	
	@Autowired
    private ApplicationContext context;

    private WebTestClient webClient;

    @BeforeEach
    void setup() {
        webClient = WebTestClient.bindToApplicationContext(context)
        	.configureClient()
        	.build();       
    }

}
