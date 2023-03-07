package com.sahaj.parkinglot;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FeeCalculatorTest {
    private FeeCalculator calculator = new FeeCalculator();

    @Test
    public void testCalculate() {
        assertThat(calculator.calculate("mpl","m", 3)).isEqualTo(0);
    }
}