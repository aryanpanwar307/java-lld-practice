package com.parkinglot.model;

public abstract class Vehicle {
    private final String licenseNo;
    private final VehicleType type;

    public Vehicle(String licenseNo, VehicleType type) {
        this.licenseNo = licenseNo;
        this.type = type;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public VehicleType getType() {
        return type;
    }

}
