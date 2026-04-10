package com.parkinglot.model;

import java.time.*;
import java.util.UUID;

/**
 * Empty class for practice.
 */
public class Ticket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(Vehicle vehicle, ParkingSpot spot) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
        this.exitTime = null;
    }

    public void close() {
        if (exitTime != null) {
            throw new IllegalStateException("Ticket already closed");
        }
        this.exitTime = LocalDateTime.now();
    }

    public double getDurationHours() {
        LocalDateTime endTime = (exitTime != null) ? exitTime : LocalDateTime.now();

        return Duration.between(entryTime, endTime).toMinutes() / 60.0;

    }

    public boolean isTicketActive() {
        return exitTime == null;
    }

    // getters
    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }
}
