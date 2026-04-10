package com.parkinglot.model;

/**
 * Empty class for practice.
 */
public class FeeCalculator {
    private final double ticketFareBike = 15.0;
    private final double ticketFareCar = 20.0;
    private final double ticketFareTruck = 30.0;
    private final double minimumFare = 10.0;

    public double calculateActiveFare(Ticket ticket) {
        double activeDuration = ticket.getDurationHours();
        double fareTillNow = getRateForVehicle(ticket.getVehicle().getType());
        double finalTotal = activeDuration * fareTillNow;

        return Math.max(minimumFare, finalTotal);
    }

    public double getRateForVehicle(VehicleType type){
        switch (type) {
            case CAR: return ticketFareCar;
            case BIKE: return ticketFareBike;
            case TRUCK: return ticketFareTruck;
            default: throw new IllegalArgumentException("invalid vehicle type");
        }
    }

}
