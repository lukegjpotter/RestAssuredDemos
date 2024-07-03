package com.lukegjpotter.restassured.RestAssuredDemos.lotto.controller;

import com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto.LottoDrawHistoryDto;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto.NumbersCheckDto;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.service.LottoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lotto")
public class LottoApiController {

    private final LottoService lottoService;
    private final Logger logger = LoggerFactory.getLogger(LottoApiController.class);

    public LottoApiController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @GetMapping("/check/{numbers}")
    public ResponseEntity<NumbersCheckDto> checkNumbers(@PathVariable List<Integer> numbers) {
        logger.trace("Check Numbers endpoint called.");

        NumbersCheckDto numberCheckDto = lottoService.checkNumbers(numbers);

        if (numberCheckDto.errorMessage().isEmpty()) return ResponseEntity.ok(numberCheckDto);
        else return ResponseEntity.badRequest().body(numberCheckDto);
    }

    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        logger.trace("Health endpoint called.");

        return ResponseEntity.ok("Alive");
    }

    @GetMapping("/history")
    public ResponseEntity<LottoDrawHistoryDto> getDrawHistory() {
        logger.trace("Draw History endpoint called.");

        return ResponseEntity.ok(lottoService.getDrawHistory());
    }
}
