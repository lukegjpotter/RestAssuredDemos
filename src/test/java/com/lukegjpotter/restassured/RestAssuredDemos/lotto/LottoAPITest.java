package com.lukegjpotter.restassured.RestAssuredDemos.lotto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

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

    @Test
    public void checkNumbers_winningNumbers() {
        when()
                .get("/check/1,2,3,4,5,6")
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("result", equalTo("Winner"));
    }

    @Test
    public void checkNumbers_losingNumbers() {
        when()
                .get("/check/7,2,3,4,5,6")
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("result", equalTo("Not this time."));
    }

    @Test
    public void checkNumbers_Error() {
        when()
                .get("/check/69,420")
                .then()
                .statusCode(200)
                .body("success", equalTo(false));
    }

    @Test
    public void drawHistory_containing42() {
        when()
                .get("/history")
                .then()
                .body("lottodraws.findAll { it.winningNumbers.contains(42) }.drawDateTime",
                        hasItems("2004-08-16T23:42:00", "2024-03-15T19:00:00"));
    }
}
