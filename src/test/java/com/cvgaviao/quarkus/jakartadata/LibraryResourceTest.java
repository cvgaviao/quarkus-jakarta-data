package com.cvgaviao.quarkus.jakartadata;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class LibraryResourceTest {

	@BeforeEach
	void beforeEach() {
		given().when().log().all().body("{\"isbn\" : \"120\", \"title\" : \"MyLife\"}").contentType("application/json")
				.post("/library/books").then().log().all().statusCode(201)
				.body(containsString("\"isbn\":"), containsString("\"title\":\"MyLife\""));

	}

	@Test
	void testDeleteBook() {
		given().when().delete("/library/books/120").then().statusCode(204);
	}
}
