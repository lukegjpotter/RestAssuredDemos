package com.lukegjpotter.restassured.RestAssuredDemos.lotto.controller;

import com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto.AdminRequestRecord;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto.LottoDrawHistoryDto;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto.NumbersCheckDto;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.exception.UserDoesntHaveRoleException;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.exception.UserNotAuthorizedException;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.service.LottoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/triggerdraw")
    public ResponseEntity<LottoDrawHistoryDto> triggerDraw(@RequestBody AdminRequestRecord adminRequestRecord) {

        try {
            return ResponseEntity.ok(lottoService.triggerDraw(adminRequestRecord));
        } catch (UserNotAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LottoDrawHistoryDto(null, "Error: User not Authorized to Access this Endpoint"));
        } catch (UserDoesntHaveRoleException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new LottoDrawHistoryDto(null, "Error: User not Allowed to Access this Endpoint"));
        }
    }
}
