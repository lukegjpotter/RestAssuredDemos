package com.lukegjpotter.restassured.RestAssuredDemos.lotto.service;

import com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto.AdminRequestRecord;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto.LottoDrawDto;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto.LottoDrawHistoryDto;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.dto.NumbersCheckDto;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.exception.UserDoesntHaveRoleException;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.exception.UserNotAuthorizedException;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.model.AdminAndRole;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.model.LottoDraw;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.repository.AdminAndRolesRepository;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.repository.LottoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class LottoService {

    private final Logger logger = LoggerFactory.getLogger(LottoService.class);
    private final LottoRepository lottoRepository;
    private final AdminAndRolesRepository adminAndRolesRepository;
    private final static int DRAW_SIZE = 6;

    public LottoService(LottoRepository lottoRepository, AdminAndRolesRepository adminAndRolesRepository) {
        this.lottoRepository = lottoRepository;
        this.adminAndRolesRepository = adminAndRolesRepository;
    }

    public NumbersCheckDto checkNumbers(List<Integer> numbers) {
        logger.trace("Numbers Supplied: {}", numbers);
        if (numbers.size() != DRAW_SIZE) return new NumbersCheckDto("", "Error: You must supply six numbers");

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

    public LottoDrawHistoryDto triggerDraw(AdminRequestRecord adminRequestRecord) throws UserNotAuthorizedException, UserDoesntHaveRoleException {
        AdminAndRole adminAndRole = adminAndRolesRepository.findByBearerToken(adminRequestRecord.bearerToken());

        if (adminAndRole == null) throw new UserNotAuthorizedException();
        if (!adminAndRole.getRole().equals("TriggerDraw")) throw new UserDoesntHaveRoleException();

        Random rng = new Random();
        Set<Integer> generated = new LinkedHashSet<>();
        while (generated.size() < DRAW_SIZE) {
            Integer next = rng.nextInt(42) + 1;
            generated.add(next);
        }

        LottoDraw lottoDraw = lottoRepository.save(new LottoDraw(new ArrayList<>(generated), LocalDateTime.now()));
        LottoDrawDto lottoDrawDto = new LottoDrawDto(lottoDraw.getWinningNumbers(), lottoDraw.getDrawDateTime());

        return new LottoDrawHistoryDto(List.of(lottoDrawDto), "");
    }
}
