package com.tecacet.finance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Split {

    private final LocalDate date;
    private final int numerator;
    private final int denominator;

    public boolean isReverse() {
        return numerator > denominator;
    }

    @Override
    public String toString() {
        return String.format("%d:%d on %s", denominator, numerator, date);
    }
}
