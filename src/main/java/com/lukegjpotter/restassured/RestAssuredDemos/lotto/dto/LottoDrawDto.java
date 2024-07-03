package com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto;

import java.time.LocalDateTime;
import java.util.List;

public record LottoDrawDto(List<Integer> winningNumbers, LocalDateTime drawDateTime) {
}
