package com.parkinglot.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Empty class for practice.
 */
public class ParkingFloor {
    private final int floorNo;
    private final List<ParkingSpot> spots;

    public ParkingFloor(int floorNo) {
        this.floorNo = floorNo;
        this.spots = new ArrayList<>();
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public ParkingSpot isSpotAvailable(VehicleType type) {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable() && spot.canFit(type))
                return spot;
        }
        return null;
    }

    public int getAvailableCount() {
        return (int) spots.stream()
                .filter(ParkingSpot::isAvailable)
                .count();
    }

    public void getStatus() {
        System.out.println("Floor" + floorNo + ":");
        for (ParkingSpot spot : spots) {
            String status = spot.isAvailable() ? "FREE" : "OCCUPIED";
            System.out.printf("Spot %-3d [%-6s] %s%n", spot.getSpotNumber(), spot.getSpotType(), status);
        }
    }

    public int getFloorNo() {
        return floorNo;
    }

}
