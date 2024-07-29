package com.lukegjpotter.restassured.RestAssuredDemos.pokemon.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PokeApiControllerTest {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://pokeapi.co/api/v2";
    }

    @Test
    void pokemon_Charizard_HiddenAbilitySolarPower() {
        RestAssured.given().contentType(ContentType.JSON)
                .when().get("/pokemon/charizard")
                .then().statusCode(HttpStatus.SC_OK)
                .body("abilities.ability[1].name", equalTo("solar-power"),
                        "abilities.is_hidden[1]", is(true));
    }
}
