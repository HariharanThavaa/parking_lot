package com.sahaj.parkinglot;

import java.util.Map;


import static com.sahaj.parkinglot.FeeCalculator.Venue.SMALL;
import static com.sahaj.parkinglot.FeeCalculator.Venue.MALL;
import static com.sahaj.parkinglot.FeeCalculator.Venue.STADIUM;
import static com.sahaj.parkinglot.FeeCalculator.Spot.MOTORCYCLE;
import static com.sahaj.parkinglot.FeeCalculator.Spot.CARORSUV;
import static com.sahaj.parkinglot.FeeCalculator.Spot.BUSORTRUCK;

public class FeeCalculator {

    public static enum Venue{
        SMALL, MALL, STADIUM, AIRPORT
    }

    public static enum Spot{
        MOTORCYCLE, CARORSUV, BUSORTRUCK
    }

    private Map<Venue, Map <Spot, Integer>> feeModel = Map.of(SMALL, Map.of(MOTORCYCLE, 10),
            MALL, Map.of(MOTORCYCLE, 10, CARORSUV, 20, BUSORTRUCK, 50));

    public int calculate(final Venue venue,
                         final Spot spot,
                         final double hours) throws IllegalArgumentException{
        if ( venue == SMALL && spot != MOTORCYCLE)
            throw new IllegalArgumentException("Invalid input");

        return (int)(feeModel.get(venue).get(spot) * Math.ceil(hours));
    }


}
