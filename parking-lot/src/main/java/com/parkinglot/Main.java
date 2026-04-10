package com.parkinglot;

import com.parkinglot.model.*;

public class Main {
    public static void main(String[] args) {
        ParkingLot park1 = new ParkingLot("LOT A");

        ParkingFloor floor1 = new ParkingFloor(1);
        ParkingFloor floor2 = new ParkingFloor(2);

        ParkingSpot spot1 = new ParkingSpot(1, SpotType.LARGE);
        ParkingSpot spot2 = new ParkingSpot(2, SpotType.SMALL);
        ParkingSpot spot3 = new ParkingSpot(3, SpotType.SMALL);
        ParkingSpot spot4 = new ParkingSpot(4, SpotType.MEDIUM);
        ParkingSpot spot5 = new ParkingSpot(5, SpotType.MEDIUM);

        floor1.addSpot(spot3);
        floor1.addSpot(spot4);
        floor1.addSpot(spot5);
        floor2.addSpot(spot1);
        floor2.addSpot(spot2);

        park1.addFloor(floor1);
        park1.addFloor(floor2);

        Vehicle carA = new Car("1aassddff");
        Vehicle bikeA = new Bike("2ddsseerr");
        Vehicle truckA = new Truck("3qqwweerr");
        Vehicle carB = new Car("4ttrreeww");

        Ticket t3 = park1.park(truckA);
        Ticket t1 = park1.park(carA);
        Ticket t2 = park1.park(bikeA);
        Ticket t4 = park1.park(carB);

        System.out.println("Parked car,  ticket: " + t1.getTicketId());
        System.out.println("Parked bike, ticket: " + t2.getTicketId());
        System.out.println("Parked truck, ticket: " + t3.getTicketId());

        floor1.getStatus();

        double fee = park1.unpark(t1);
        System.out.printf("Car exited. Fee: ₹%.2f%n", fee);

        // try {
        // park1.park(bikeA);
        // } catch (IllegalArgumentException e) {
        // System.out.println("catch error" + e.getMessage());
        // }

    }
}
