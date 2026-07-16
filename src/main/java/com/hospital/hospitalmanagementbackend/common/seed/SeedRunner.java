package com.hospital.hospitalmanagementbackend.common.seed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(
        name = "app.seed.enabled",
        havingValue = "true"
)
public class SeedRunner implements CommandLineRunner {

    private final List<Seeder> seeders;

    @Override
    public void run(String... args) {

        log.info("==========================================");
        log.info("Starting Development Data Seeding");
        log.info("==========================================");

        seeders.stream()
                .sorted(Comparator.comparingInt(Seeder::getOrder))
                .forEach(seeder -> {

                    log.info("Running {}", seeder.getName());

                    seeder.seed();

                });

        log.info("==========================================");
        log.info("Development Data Seeding Completed");
        log.info("==========================================");
    }
}