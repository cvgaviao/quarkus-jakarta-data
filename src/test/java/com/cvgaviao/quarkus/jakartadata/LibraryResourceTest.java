package com.cvgaviao.quarkus.jakartadata;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class LibraryResourceTest {

	void beforeEach() {
		given().when().body("{\"isbn\" : \"120\", \"title\" : \"MyLife\"}").contentType("application/json")
				.post("/library/books").then().statusCode(201)
				.body(containsString("\"id\":"), containsString("\"name\":\"Pear\""));

	}

	@Test
	void testDeleteBook() {
		given().when().delete("/library/books/120").then().statusCode(200);
	}
}
