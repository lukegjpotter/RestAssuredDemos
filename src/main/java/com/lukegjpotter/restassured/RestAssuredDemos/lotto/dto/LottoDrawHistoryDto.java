package com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto;

import com.lukegjpotter.restassured.RestAssuredDemos.lotto.model.LottoDraw;

import java.util.List;

public record LottoDrawHistoryDto(List<LottoDraw> lottodraws, String errorMessage) {
}
