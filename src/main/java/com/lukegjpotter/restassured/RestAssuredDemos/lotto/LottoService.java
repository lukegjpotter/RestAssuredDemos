package com.lukegjpotter.restassured.RestAssuredDemos.lotto;

import org.springframework.stereotype.Service;

@Service
class LottoService {

    LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }
}
