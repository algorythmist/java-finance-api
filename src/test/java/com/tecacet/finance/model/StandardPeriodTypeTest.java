package com.tecacet.finance.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class StandardPeriodTypeTest {

    @Test
    public void getPeriodsInYear() {
        assertEquals(1, StandardPeriodType.YEAR.getPeriodsInYear());
        assertEquals(12, StandardPeriodType.MONTH.getPeriodsInYear());
        assertEquals(52, StandardPeriodType.WEEK.getPeriodsInYear());
        assertEquals(365, StandardPeriodType.DAY.getPeriodsInYear());
    }
}