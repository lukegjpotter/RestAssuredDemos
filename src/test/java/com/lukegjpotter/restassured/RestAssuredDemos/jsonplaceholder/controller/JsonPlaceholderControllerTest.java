package com.lukegjpotter.restassured.RestAssuredDemos.jsonplaceholder.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class JsonPlaceholderControllerTest {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    void testPosts_PostOne() {
        RestAssured.given().contentType(ContentType.JSON)
                .when().get("/posts/1")
                .then().statusCode(HttpStatus.SC_OK)
                .body("userId", equalTo(1),
                        "id", equalTo(1),
                        "title", containsString("sunt"),
                        "body", startsWith("quia et suscipit"));
    }
}
