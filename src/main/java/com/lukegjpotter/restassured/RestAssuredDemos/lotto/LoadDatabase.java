package com.lukegjpotter.restassured.RestAssuredDemos.lotto;

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
    CommandLineRunner initDatabase(LottoRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new LottoDraw(Arrays.asList(4, 8, 15, 16, 23, 42), LocalDateTime.parse("2004-08-16T23:42:00"))));
            log.info("Preloading " + repository.save(new LottoDraw(Arrays.asList(5, 10, 12, 34, 35, 42), LocalDateTime.parse("2024-03-15T19:00:00"))));
            // todo log.info("Preloading " + repository.save(new LottoDraw(Arrays.asList(1, 2, 3, 4, 5, 6), LocalDateTime.now()))));
        };
    }
}
