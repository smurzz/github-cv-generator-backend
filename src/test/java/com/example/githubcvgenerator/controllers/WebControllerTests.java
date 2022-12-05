package com.example.githubcvgenerator.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import com.example.githubcvgenerator.GithubCvGeneratorApplication;
import com.example.githubcvgenerator.config.WebClientConfig;
import com.example.githubcvgenerator.model.Resume;
import com.example.githubcvgenerator.service.ResumeService;

import reactor.core.publisher.Mono;

// @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebClientConfig.class)
// @EnableWebFluxSecurity
// @EnableReactiveMethodSecurityO
// @WebFluxTest(ResumeController.class)
// @AutoConfigureWebTestClient
public class WebControllerTests {

	@Autowired
    private WebApplicationContext context;

    private WebTestClient webClient;

    @BeforeEach
    void setup() {
        webClient = MockMvcWebTestClient.bindToApplicationContext(context)
                .apply(springSecurity())
                .defaultRequest(get("/").with(oidcLogin()))
                .configureClient()
                .build();
    }

	@MockBean
	private ResumeService resumeService;

	@Test
	void getResumeByLoginTest() throws InterruptedException, ExecutionException {
		Resume smurrr = new Resume();
		smurrr.setDateOfCreation("2021-10-29T16:39:30Z");
		smurrr.setLoginOwner("Smurrr");

		given(this.resumeService.getResumeByLogin("smurrr")).willReturn(Mono.just(smurrr));
		
		EntityExchangeResult<Resume> resumeResult = webClient.get().uri("/resume/smurrr")
//				.headers(http -> http.setBasicAuth("smurzz", "Office1991!"))
				.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBody(Resume.class)
				.returnResult();
		Resume resume = resumeResult.getResponseBody();
		assertEquals("Smurrr", resume.getLoginOwner());
		assertEquals("2021-10-29T16:39:30Z", resume.getDateOfCreation());
	}
}


//
//@Value("${spring.security.oauth2.client.registration.github.client-id}")
//String clientId;
//
//@Value("${spring.security.oauth2.client.registration.github.client-secret}")
//String clientSecret;
//
//@Value("${spring.security.oauth2.client.provider.github.token-uri}")
//String tokenUri;
//
//@Value("${spring.security.oauth2.client.registration.github.redirect-uri}")
//String redirectUri;
//
//@Test
//void getStart(@Autowired WebTestClient webClient) throws InterruptedException, ExecutionException {
//	webClient.get().uri("/").exchange().expectStatus().isFound();
//}
//
//@Test
//void getAuthorization(@Autowired WebTestClient webClient) throws InterruptedException, ExecutionException {
//	webClient.get().uri("/oauth2/authorization/github").exchange().expectStatus().isFound();
//}
//
//@Test
//void getAuthorize(@Autowired WebTestClient webClient) throws InterruptedException, ExecutionException {
//	webClient.get().uri(uriBuilder -> uriBuilder
//			.path("https://github.com/login/oauth/authorize")
//			.queryParam("response_type", "code")
//			.queryParam("client_id", "{clientId}")
//			.queryParam("scope", "read:user")
//			.queryParam("state", "T6ojMVoiJveHMb3r7srhibqonW2DczCziBy0ls7le5Q=")
//			.queryParam("redirect_uri", "${redirectUri}")
//			.build(clientId, redirectUri)).exchange().expectStatus().isFound();
//}
//
//@Test
//void getLogin(@Autowired WebTestClient webClient) throws InterruptedException, ExecutionException {
//	webClient.get().uri(uriBuilder -> uriBuilder
//			.path("https://github.com/login")
//			.queryParam("client_id", "{clientId}")
//			.queryParam("return_to", "/login/oauth/authorize?client_id=Iv1.a87db467e7241671&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Flogin%2Foauth2%2Fcode%2Fgithub&response_type=code&scope=read%3Auser&state=T6ojMVoiJveHMb3r7srhibqonW2DczCziBy0ls7le5Q%3D")
//			.build(clientId)).exchange().expectStatus().isFound();
//}
