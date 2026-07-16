package com.hospital.hospitalmanagementbackend.common.seed;

public interface Seeder {

    void seed();

    int getOrder();

    String getName();
}