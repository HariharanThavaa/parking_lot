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
        expectException(SMALL, CARORSUV, 1);
        expectException(SMALL, BUSORTRUCK, 1);
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
        validate(MALL, CARORSUV, 1, 20);
        validate(MALL, CARORSUV, 2, 40);
        validate(MALL, CARORSUV, 3, 60);
        validate(MALL, CARORSUV, 1.5, 40);
    }

    @Test
    public void testCalculateMallVenueBusSpot() {
        validate(MALL, BUSORTRUCK, 1, 50);
        validate(MALL, BUSORTRUCK, 2, 100);
        validate(MALL, BUSORTRUCK, 3, 150);
        validate(MALL, BUSORTRUCK, 1.5, 100);
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
}