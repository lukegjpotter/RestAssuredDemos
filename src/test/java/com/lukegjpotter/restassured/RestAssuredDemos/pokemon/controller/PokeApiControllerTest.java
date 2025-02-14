package com.lukegjpotter.restassured.RestAssuredDemos.pokemon.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.*;

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
                .body("abilities.ability[1].name", is("solar-power"),
                        "abilities.is_hidden[1]", is(true));
    }

    @Test
    void ability_SolarPower_LesserKnownPokemon() {
        RestAssured.given().contentType(ContentType.JSON)
                .when().get("/ability/solar-power")
                .then().statusCode(HttpStatus.SC_OK)
                .body("pokemon.pokemon.name", hasItems("tropius", "sunkern", "sunflora", "helioptile", "heliolisk"))
                .body("pokemon.pokemon", hasSize(greaterThanOrEqualTo(5)));
    }

    @Test
    void moveLearnedBy_Transform_MewDitto() {
        RestAssured.given().contentType(ContentType.JSON)
                .when().get("/move/transform")
                .then().statusCode(HttpStatus.SC_OK)
                .body("learned_by_pokemon.name", hasItems("ditto", "mew"))
                .body("learned_by_pokemon.name", hasSize(2));
    }

    @Test
    void pokemonDetails_Mew() {
        RestAssured.given().contentType(ContentType.JSON)
                .when().get("/pokemon/mew")
                .then().statusCode(HttpStatus.SC_OK)
                .body("name", is("mew"))
                .body("id", is(151))
                .body("game_indices.version.name", hasItems("red", "blue", "yellow"))
                .body("types.type.name", hasSize(1))
                .body("types.type.name", hasItem("psychic"))
                .body("abilities", hasSize(1))
                .body("abilities.ability.name", hasItem("synchronize"))
                .body("stats.base_stat", everyItem(is(100)));
    }
}
