package com.lukegjpotter.restassured.RestAssuredDemos.lotto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
class LottoService {

    private final Logger logger = LoggerFactory.getLogger(LottoService.class);
    LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public NumbersCheckDto checkNumbers(List<Integer> numbers) {
        if (numbers.size() != 6) return new NumbersCheckDto(false, "error");

        Collections.sort(numbers);
        List<Integer> mostRecentDrawNumbers = lottoRepository.findTopByOrderByDrawDateTimeDesc().getWinningNumbers();
        Collections.sort(mostRecentDrawNumbers);

        if (numbers.equals(mostRecentDrawNumbers)) return new NumbersCheckDto(true, "Winner");
        else return new NumbersCheckDto(true, "Not this time.");
    }

    public LottoDrawHistoryDto getDrawHistory() {
        return new LottoDrawHistoryDto(lottoRepository.findAll());
    }
}
