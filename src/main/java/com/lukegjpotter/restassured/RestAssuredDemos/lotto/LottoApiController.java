package com.lukegjpotter.restassured.RestAssuredDemos.lotto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lotto")
public class LottoApiController {

    private LottoService lottoService;
    private final Logger logger = LoggerFactory.getLogger(LottoApiController.class);

    public LottoApiController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @GetMapping("/check/{numbers}")
    public NumbersCheckDto checkNumbers(@PathVariable List<Integer> numbers) {
        logger.trace("Check Numbers endpoint called.");

        return lottoService.checkNumbers(numbers);
    }

    @GetMapping("/health")
    public String getHealth() {
        logger.trace("Health endpoint called.");

        return "Alive";
    }
}
