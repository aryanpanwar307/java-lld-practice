package com.parkinglot.model;

/**
 * Empty class for practice.
 */
public class ParkingSpot {
    private final int spotNo;
    private final SpotType spotType;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotNo, SpotType spotType) {
        this.spotNo = spotNo;
        this.spotType = spotType;
        this.parkedVehicle = null;
    }

    public boolean canFit(VehicleType vehicleType) {
        switch (vehicleType) {
            case BIKE:
                return spotType == SpotType.SMALL || spotType == SpotType.MEDIUM || spotType == SpotType.LARGE;
            case CAR:
                return spotType == SpotType.MEDIUM || spotType == SpotType.LARGE;
            case TRUCK:
                return spotType == SpotType.LARGE;
            default:
                return false;
        }
    }

    public boolean isAvailable() {
        return parkedVehicle == null;
    }

    public void assignVehicle(Vehicle vehicle) {
        if (!isAvailable()) {
            throw new IllegalStateException("spot is occupied");
        }
        this.parkedVehicle = vehicle;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
    }

    public int getSpotNumber() {
        return spotNo;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

}
