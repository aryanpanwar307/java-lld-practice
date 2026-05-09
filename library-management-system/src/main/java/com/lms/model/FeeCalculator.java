package com.lms.model;

public class FeeCalculator {

    private static final double FINE_PER_DAY = 5.0;

    public double calculateFine(BookRecord record) {
        long daysOverdue = record.getDaysOverdue();
        return daysOverdue * FINE_PER_DAY;
    }
}