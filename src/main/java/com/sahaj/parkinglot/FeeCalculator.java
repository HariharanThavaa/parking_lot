package com.sahaj.parkinglot;

import java.util.List;
import java.util.Map;

import static com.sahaj.parkinglot.FeeCalculator.Venue.SMALL;
import static com.sahaj.parkinglot.FeeCalculator.Venue.MALL;
import static com.sahaj.parkinglot.FeeCalculator.Venue.STADIUM;
import static com.sahaj.parkinglot.FeeCalculator.Spot.MOTORCYCLE;
import static com.sahaj.parkinglot.FeeCalculator.Spot.CARORSUV;
import static com.sahaj.parkinglot.FeeCalculator.Spot.BUSORTRUCK;

public class FeeCalculator {

    public static enum Venue {
        SMALL, MALL, STADIUM, AIRPORT
    }

    public static enum Spot {
        MOTORCYCLE, CARORSUV, BUSORTRUCK
    }

    private Map<Venue, Map <Spot, Rate>> feeModel =
            Map.of(
                    SMALL, Map.of(MOTORCYCLE, new Rate(10)),
                    MALL, Map.of(MOTORCYCLE, new Rate(10),
                            CARORSUV, new Rate(20),
                            BUSORTRUCK, new Rate(50)),
                    STADIUM, Map.of(MOTORCYCLE, new Rate(100, 12, List.of(new FixedRate(0, 4, 30), new FixedRate(4, 12, 60))),
                            CARORSUV, new Rate(200, 12, List.of(new FixedRate(0, 4, 60), new FixedRate(4, 12, 120)))));

    public int calculate(final Venue venue,
                         final Spot spot,
                         final double hours) throws IllegalArgumentException{

        if ( venue == SMALL && spot != MOTORCYCLE)
            throw new IllegalArgumentException("Invalid input");

        if ( venue == STADIUM && spot == BUSORTRUCK)
            throw new IllegalArgumentException("Invalid input");

        int calhours = (int)Math.ceil(hours);

        Rate rate = feeModel.get(venue).get(spot);

        int fees = 0;

        if (calhours > rate.getHourlyRateFrom())
            fees = (calhours - rate.getHourlyRateFrom()) * rate.getHourlyRate();

        if (rate.getFixedRates().isEmpty()) {
            return fees;
        } else {
            for (FixedRate fr : rate.getFixedRates()) {
                if ( fr.getFrom() < calhours )
                    fees = fees + fr.getRate();

            }
            return  fees;
        }
    }


}
