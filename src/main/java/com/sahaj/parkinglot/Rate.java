package com.sahaj.parkinglot;

import java.util.Collections;
import java.util.List;

public class Rate {

    private final int hourlyRate;

    private final int hourlyRateFrom;

    private final List<FixedRate> fixedRates;

    public Rate(final int hourlyRate,
                final int hourlyRateFrom,
                final List<FixedRate> fixedRates){
        this.hourlyRate = hourlyRate;
        this.hourlyRateFrom = hourlyRateFrom;
        this.fixedRates = fixedRates;
    }

    public Rate(int hourlyRate){
        this(hourlyRate, 0, Collections.emptyList());
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public int getHourlyRateFrom() {
        return hourlyRateFrom;
    }

    public List<FixedRate> getFixedRates() {
        return fixedRates;
    }
}
