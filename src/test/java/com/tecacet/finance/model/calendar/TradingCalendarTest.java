package com.tecacet.finance.model.calendar;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Collections;

public class TradingCalendarTest {

    @Test
    public void testToString() {
        TradingCalendar tradingCalendar = new TradingCalendar(2019, 2, Collections.emptyList());
        assertEquals("TradingCalendar(month=2019, year=2)", tradingCalendar.toString());
    }
}
