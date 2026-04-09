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

class Car extends Vehicle {
    public Car(String plate) {
        super(plate, VehicleType.CAR);
    }
}

class Bike extends Vehicle {
    public Bike(String plate) {
        super(plate, VehicleType.BIKE);
    }
}

class Truck extends Vehicle {
    public Truck(String plate) {
        super(plate, VehicleType.TRUCK);
    }
}
