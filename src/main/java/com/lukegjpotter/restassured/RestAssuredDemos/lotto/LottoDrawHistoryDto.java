package com.lukegjpotter.restassured.RestAssuredDemos.lotto;

import java.util.List;

public record LottoDrawHistoryDto(List<LottoDraw> lottodraws, String errorMessage) {
}
