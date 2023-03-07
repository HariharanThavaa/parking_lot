package com.sahaj.parkinglot;

public class FixedRate {

    private final int from;

    private final int rate;

    public FixedRate(int from, int rate) {
        this.from = from;
        this.rate = rate;
    }

    public int getFrom() {
        return from;
    }

    public int getRate() {
        return rate;
    }
}
