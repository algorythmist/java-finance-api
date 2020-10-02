package com.tecacet.finance.services.tradingday;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class TradierTradingDayServiceTest  {

    @Test
    public void getCalendar() throws IOException {
        TradierTradingDayService tradingDayService = new TradierTradingDayService();
        Calendar calendar = tradingDayService.getCalendar(2019, 12);
        List<Day> days = calendar.getDays();
        assertEquals(31, days.size());
        Set<Day> holidays = tradingDayService.getHolidays(days);
        assertEquals(1, holidays.size());
        Day holiday = holidays.stream().findFirst().get();
        assertEquals(LocalDate.of(2019, 12, 25), holiday.getDate());
        assertEquals("Market is closed for Christmas Day", holiday.getDescription());
        assertTrue(holiday.isHoliday());
        assertFalse(holiday.isEarlyClose());
        assertFalse(holiday.isWeekend());
        assertNull("", holiday.getStartTime());
        assertNull("", holiday.getEndTime());

        Set<Day> earlyCloseDays = tradingDayService.getEarlyCloseDays(days);
        assertEquals(1, earlyCloseDays.size());
        Day earlyDay = earlyCloseDays.stream().findFirst().get();

        assertEquals(LocalDate.of(2019, 12, 24), earlyDay.getDate());
        assertEquals("Market closes early at 13:00", earlyDay.getDescription());
        assertFalse(earlyDay.isHoliday());
        assertTrue(earlyDay.isEarlyClose());
        assertFalse(earlyDay.isWeekend());
    }

    @Test
    public void getDaysInYear() throws IOException {
        TradierTradingDayService tradingDayService = new TradierTradingDayService();
        List<Day> days = tradingDayService.getDays(2019);
        Set<Day> holidays = tradingDayService.getHolidays(days);
        assertEquals(9, holidays.size());
        Set<Day> earlyCloseDays = tradingDayService.getEarlyCloseDays(days);
        assertEquals(3, earlyCloseDays.size());
    }
}