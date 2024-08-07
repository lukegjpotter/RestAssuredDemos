package com.lukegjpotter.restassured.RestAssuredDemos.lotto.repository;

import com.lukegjpotter.restassured.RestAssuredDemos.lotto.model.LottoDraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(readOnly = true)
public interface LottoRepository extends JpaRepository<LottoDraw, UUID> {
    LottoDraw findTopByOrderByDrawDateTimeDesc();
}
