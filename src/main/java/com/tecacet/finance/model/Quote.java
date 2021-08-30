package com.tecacet.finance.model;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class Quote implements Serializable {

    private LocalDate date;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;
    private double adjustedClose;

    @Override
    public String toString() {
        return String.format("%s %f %d", date.toString(), adjustedClose, volume);
    }

}
