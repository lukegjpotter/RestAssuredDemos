package com.lukegjpotter.restassured.RestAssuredDemos.lotto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface LottoRepository extends JpaRepository<LottoDraw, UUID> {
    LottoDraw findTopByOrderByDrawDateTimeDesc();
}
