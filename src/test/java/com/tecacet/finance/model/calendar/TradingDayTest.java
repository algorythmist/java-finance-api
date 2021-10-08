package com.tecacet.finance.model.calendar;

import static org.junit.Assert.*;

import org.junit.Test;

import java.time.LocalDate;

public class TradingDayTest {

    @Test
    public void testToString() {
        TradingDay tradingDay = TradingDay.builder()
                .date(LocalDate.of(2022, 4, 1))
                .description("Market closed for April Fool's day")
                .marketStatus(TradingDay.MarketStatus.CLOSED)
                .build();
        assertEquals("TradingDay(date=2022-04-01, description=Market closed for April Fool's day, marketStatus=CLOSED)",
                tradingDay.toString());
    }
}
