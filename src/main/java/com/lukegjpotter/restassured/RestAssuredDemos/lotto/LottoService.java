package com.lukegjpotter.restassured.RestAssuredDemos.lotto;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LottoService {

    LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public NumbersCheckDto checkNumbers(List<Integer> numbers) {
        // fixme Ensure that there's 6 numbers.
        // todo Find the Latest (by DateTime) Draw, and Compare numbers.
        return new NumbersCheckDto("Winner");
    }
}
