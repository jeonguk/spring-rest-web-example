package com.jeonguk.web;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = SpringRestWebApplication.class,
		webEnvironment = RANDOM_PORT
)
@ActiveProfiles("test")
public abstract class BaseTest {

	@LocalServerPort
	private int port;

	private String HOST_ROOT = "http://localhost/api/1.0/";

	Response preparePost(String path, String body) {
		return preparePostPutWhen(body)
				.post(HOST_ROOT + path);
	}

	private RequestSpecification preparePostPutWhen(String body) {
		return given()
				.port(port)
//				.auth()
//				.basic(USER_NAME, PASSWORD)
				.contentType(String.valueOf(APPLICATION_JSON))
				.body(body)
				.when();
	}
}
