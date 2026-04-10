package com.parkinglot.model;

import java.util.*;

/**
 * Empty class for practice.
 */
public class ParkingLot {
    private String name;
    private List<ParkingFloor> floors;
    private Map<String, Ticket> activeTickets;

    // constructor
    public ParkingLot(String name) {
        this.name = name;
        this.floors = new ArrayList<>();
        this.activeTickets = new HashMap<>();
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public Ticket park(Vehicle vehicle) {
        if (activeTickets.containsKey(vehicle.getLicenseNo()))
            throw new IllegalArgumentException("Vehicle already parked");

        ParkingSpot spot = findAvailableSpot(vehicle.getType());
        if (spot == null)
            throw new IllegalArgumentException("All spots are full");

        spot.assignVehicle(vehicle);
        Ticket ticket = new Ticket(vehicle, spot);
        activeTickets.put(vehicle.getLicenseNo(), ticket);
        return ticket;

    }

    public double unpark(Ticket ticket) {
        if (!activeTickets.containsKey(ticket.getVehicle().getLicenseNo()))
            throw new IllegalArgumentException("Already unparked");

        ticket.getSpot().removeVehicle();
        ticket.close();

        double amount = new FeeCalculator().calculateActiveFare(ticket);
        activeTickets.remove(ticket.getVehicle().getLicenseNo());
        return amount;
    }

    public Ticket getTicket(String licenseNo) {
        return activeTickets.get(licenseNo);
    }

    public int getAvailableSpotCount() {
        return floors.stream().mapToInt(ParkingFloor::getAvailableCount).sum();
    }

    public ParkingSpot findAvailableSpot(VehicleType type) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.isSpotAvailable(type);
            if (spot != null)
                return spot;
        }
        return null;
    }

    public String getName() {
        return name;
    }

}
