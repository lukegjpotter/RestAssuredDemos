package com.lukegjpotter.restassured.RestAssuredDemos.lotto.controller;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LottoApiControllerTest {

    @BeforeAll
    static void setUp() {
        baseURI = "http://localhost:8080/lotto";
    }

    @Test
    @Order(1)
    public void lotto_healthCheck() {
        when()
                .get("/health")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkNumbers_winningNumbers() {
        when()
                .get("/check/1,2,3,4,5,6")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("result", equalTo("Winner"),
                        "errorMessage", emptyString());
    }

    @Test
    public void checkNumbers_losingNumbers() {
        when()
                .get("/check/7,2,3,4,5,6")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("result", equalTo("Not this time."),
                        "errorMessage", emptyString());
    }

    @Test
    public void checkNumbers_Error_TooFewNumbers() {
        when()
                .get("/check/69,420")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("result", emptyString(),
                        "errorMessage", equalTo("Error: You must supply six numbers"));
    }

    @Test
    public void checkNumbers_Error_TooManyNumbers() {
        when()
                .get("/check/69,420,1,2,3,4,5,6")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("result", emptyString(),
                        "errorMessage", equalTo("Error: You must supply six numbers"));
    }

    @Test
    public void drawHistory_DoesNotContainIDs() {
        when()
                .get("/history")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("lottodraws[0]", not(hasKey("id")));
    }

    @Test
    public void drawHistory_containing42() {
        when()
                .get("/history")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("lottodraws.findAll { it.winningNumbers.contains(42) }.drawDateTime",
                        hasItems("2004-08-16T23:42:00", "2024-03-15T19:00:00"));
    }
}
