package com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto;

import java.util.List;

public record LottoDrawHistoryDto(List<LottoDrawDto> lottodraws, String errorMessage) {
}
