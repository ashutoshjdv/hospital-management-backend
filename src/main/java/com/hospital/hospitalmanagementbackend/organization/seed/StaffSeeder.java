package com.hospital.hospitalmanagementbackend.organization.seed;

import com.hospital.hospitalmanagementbackend.common.seed.Seeder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StaffSeeder implements Seeder {

    @Override
    public void seed() {

        log.info("Staff Seeder Started");

        // Logic comes here

        log.info("Staff Seeder Completed");
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public String getName() {
        return "Staff Seeder";
    }
}