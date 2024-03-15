package com.lukegjpotter.restassured.RestAssuredDemos.lotto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lotto")
public class LottoAPIController {

    private LottoService lottoService;
    private final Logger logger = LoggerFactory.getLogger(LottoAPIController.class);

    public LottoAPIController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @GetMapping("/health")
    public String getHealth() {
        logger.trace("Health endpoint called.");

        return "Alive";
    }
}
