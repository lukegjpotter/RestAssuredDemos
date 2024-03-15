package com.lukegjpotter.restassured.RestAssuredDemos.lotto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LottoAPITest {

    @BeforeAll
    static void setUp() {
        baseURI = "http://localhost:8080/lotto";
    }

    @Test
    public void lotto_healthCheck() {
        when()
                .get("/health")
                .then()
                .statusCode(200);
    }
}
