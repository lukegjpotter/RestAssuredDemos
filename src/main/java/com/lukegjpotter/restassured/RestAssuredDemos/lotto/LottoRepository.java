package com.lukegjpotter.restassured.RestAssuredDemos.lotto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface LottoRepository extends JpaRepository<LottoDraw, UUID> {
    LottoDraw findTopByOrderByDrawDateTimeDesc();
}
