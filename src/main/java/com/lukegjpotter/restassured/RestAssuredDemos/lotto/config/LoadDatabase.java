package com.lukegjpotter.restassured.RestAssuredDemos.lotto.config;

import com.lukegjpotter.restassured.RestAssuredDemos.lotto.model.AdminAndRole;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.model.LottoDraw;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.repository.AdminAndRolesRepository;
import com.lukegjpotter.restassured.RestAssuredDemos.lotto.repository.LottoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabaseWithLottoDraws(LottoRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new LottoDraw(Arrays.asList(4, 8, 15, 16, 23, 42), LocalDateTime.parse("2004-08-16T23:42:00"))));
            log.info("Preloading " + repository.save(new LottoDraw(Arrays.asList(5, 10, 12, 34, 35, 42), LocalDateTime.parse("2024-03-15T19:00:00"))));
            log.info("Preloading " + repository.save(new LottoDraw(Arrays.asList(1, 2, 3, 4, 5, 6), LocalDateTime.now())));
        };
    }

    @Bean
    CommandLineRunner initDatabaseWithAdminUsers(AdminAndRolesRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new AdminAndRole("Adjudicator", "uevnewfiopvuebqiougbq", "TriggerDraw")));
            log.info("Preloading " + repository.save(new AdminAndRole("Staff Manager", "ifuglvieaubvllwerivbi", "ManageStaff")));
        };
    }
}
