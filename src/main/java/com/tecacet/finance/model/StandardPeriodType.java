package com.tecacet.finance.model;

public enum StandardPeriodType {

    DAY(365), WEEK(52), MONTH(12), YEAR(1);

    private final int periodsInYear;

    StandardPeriodType(int periods) {
        this.periodsInYear = periods;
    }

    public int getPeriodsInYear() {
        return periodsInYear;
    }

}
