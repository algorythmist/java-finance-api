package com.tecacet.finance.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Quote implements Serializable {

    private LocalDate date;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;
    private double adjustedClose;

    public LocalDate getDate() {
        return date;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public long getVolume() {
        return volume;
    }

    public double getAdjustedClose() {
        return adjustedClose;
    }

    @Override
    public String toString() {
        return String.format("%s %f %d", date.toString(), adjustedClose, volume);
    }

}
