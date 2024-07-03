package com.lukegjpotter.restassured.RestAssuredDemos.lotto.service;

import com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto.LottoDrawDto;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto.LottoDrawHistoryDto;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto.NumbersCheckDto;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.repository.LottoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LottoService {

    private final Logger logger = LoggerFactory.getLogger(LottoService.class);
    LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public NumbersCheckDto checkNumbers(List<Integer> numbers) {
        logger.trace("Numbers Supplied: {}", numbers);
        if (numbers.size() != 6) return new NumbersCheckDto("", "Error: You must supply six numbers");

        Collections.sort(numbers);
        List<Integer> mostRecentDrawNumbers = lottoRepository.findTopByOrderByDrawDateTimeDesc().getWinningNumbers();
        Collections.sort(mostRecentDrawNumbers);

        if (numbers.equals(mostRecentDrawNumbers)) return new NumbersCheckDto("Winner", "");
        else return new NumbersCheckDto("Not this time.", "");
    }

    public LottoDrawHistoryDto getDrawHistory() {

        List<LottoDrawDto> lottoDrawDtos = new ArrayList<>();
        lottoRepository.findAll().forEach(
                lottoDraw -> lottoDrawDtos.add(
                        new LottoDrawDto(lottoDraw.getWinningNumbers(), lottoDraw.getDrawDateTime())));

        return new LottoDrawHistoryDto(lottoDrawDtos, lottoDrawDtos.isEmpty() ? "No Draws in History." : "");
    }
}
