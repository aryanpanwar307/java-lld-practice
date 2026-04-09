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

}
