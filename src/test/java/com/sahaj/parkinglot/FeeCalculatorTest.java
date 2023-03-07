package com.sahaj.parkinglot;


import org.junit.jupiter.api.Test;

import static com.sahaj.parkinglot.FeeCalculator.Spot.*;
import static com.sahaj.parkinglot.FeeCalculator.Venue.*;
import static org.assertj.core.api.Assertions.assertThat;
import static com.sahaj.parkinglot.FeeCalculator.Venue;
import static com.sahaj.parkinglot.FeeCalculator.Spot;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FeeCalculatorTest {
    private FeeCalculator calculator = new FeeCalculator();

    @Test
    public void testCalculateSmallVenueMotoCycleSpot() {
        validate(SMALL, MOTORCYCLE, 1, 10);
        validate(SMALL, MOTORCYCLE, 2, 20);
        validate(SMALL, MOTORCYCLE, 3, 30);
        validate(SMALL, MOTORCYCLE, 1.5, 20);
    }

    @Test
    public void testCalculateSmallVenueCarSpot() {
        expectException(SMALL, CAR_OR_SUV, 1);
        expectException(SMALL, BUS_OR_TRUCK, 1);
        expectException(STADIUM, BUS_OR_TRUCK, 1);
    }

    @Test
    public void testCalculateMallVenueMotoCycleSpot() {
        validate(MALL, MOTORCYCLE, 1, 10);
        validate(MALL, MOTORCYCLE, 2, 20);
        validate(MALL, MOTORCYCLE, 3, 30);
        validate(MALL, MOTORCYCLE, 1.5, 20);
    }

    @Test
    public void testCalculateMallVenueCarSpot() {
        validate(MALL, CAR_OR_SUV, 1, 20);
        validate(MALL, CAR_OR_SUV, 2, 40);
        validate(MALL, CAR_OR_SUV, 3, 60);
        validate(MALL, CAR_OR_SUV, 1.5, 40);
    }

    @Test
    public void testCalculateMallVenueBusSpot() {
        validate(MALL, BUS_OR_TRUCK, 1, 50);
        validate(MALL, BUS_OR_TRUCK, 2, 100);
        validate(MALL, BUS_OR_TRUCK, 3, 150);
        validate(MALL, BUS_OR_TRUCK, 1.5, 100);
    }

    @Test
    public void testCalculateStadiumVenueSpotMotorCycle() {
        validate(STADIUM, MOTORCYCLE, 1, 30);
        validate(STADIUM, MOTORCYCLE, 2, 30);
        validate(STADIUM, MOTORCYCLE, 4, 30);
        validate(STADIUM, MOTORCYCLE, 12, 90);
        validate(STADIUM, MOTORCYCLE, 12.5, 190);
        validate(STADIUM, MOTORCYCLE, 13.5, 290);
    }

    @Test
    public void testCalculateStadiumVenueSpotCar() {
        validate(STADIUM, CAR_OR_SUV, 1, 60);
        validate(STADIUM, CAR_OR_SUV, 2, 60);
        validate(STADIUM, CAR_OR_SUV, 4, 60);
        validate(STADIUM, CAR_OR_SUV, 12, 180);
        validate(STADIUM, CAR_OR_SUV, 12.5, 380);
        validate(STADIUM, CAR_OR_SUV, 13.5, 580);
    }

    private void validate(final Venue venue,
                          final Spot spot,
                          final double hours,
                          final int fee) {
        assertThat(calculator.calculate( venue, spot, hours)).isEqualTo(fee);
    }

    private void expectException(final Venue venue,
                                 final Spot spot,
                                 final double hours) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            calculator.calculate(venue, spot, hours);
        }).withMessage("Invalid input");
    }

    @Test
    public void testExample2(){
        validate(MALL, MOTORCYCLE, 3.5, 40);
        validate(MALL, CAR_OR_SUV, 6.01, 140);
        validate(MALL, BUS_OR_TRUCK, 1.99, 100);
    }

    @Test
    public void testExample3(){
        validate(STADIUM, MOTORCYCLE, 3.7, 30);
        validate(STADIUM, MOTORCYCLE, 14.99, 390);
        validate(STADIUM, CAR_OR_SUV, 11.5, 180);
        validate(STADIUM, CAR_OR_SUV, 13.05, 580);
    }
}