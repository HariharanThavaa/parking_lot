package com.sahaj.parkinglot;

public class FixedRate {

    private final int from;
    private final int to;
    private final int rate;

    public FixedRate(int from, int to, int rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getRate() {
        return rate;
    }
}
