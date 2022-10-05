package com.tecacet.finance.service.calendar;

import com.tecacet.finance.model.calendar.TradingCalendar;
import com.tecacet.finance.model.calendar.TradingDay;
import com.tecacet.finance.service.calendar.tradier.TradierTradingDayService;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class TradierTradingDayServiceTest {

    private final TradingDayService tradingDayService = new TradierTradingDayService();

    @Test
    public void getCalendar() throws IOException {

        TradingCalendar calendar = tradingDayService.getCalendar(2019, 12);
        List<TradingDay> days = calendar.getTradingDays();
        assertEquals(31, days.size());

        Set<TradingDay> holidays = tradingDayService.getHolidays(days);
        assertEquals(1, holidays.size());
        TradingDay holiday = holidays.stream().findFirst().get();
        assertEquals(LocalDate.of(2019, 12, 25), holiday.getDate());
        assertEquals("Market is closed for Christmas Day", holiday.getDescription());
        assertTrue(holiday.isHoliday());
        assertFalse(holiday.isEarlyClose());
        assertFalse(holiday.isWeekend());
        assertNull(holiday.getStartTime());
        assertNull(holiday.getEndTime());

        Set<TradingDay> earlyCloseDays = tradingDayService.getEarlyCloseDays(days);
        assertEquals(1, earlyCloseDays.size());
        TradingDay earlyDay = earlyCloseDays.stream().findFirst().get();

        assertEquals(LocalDate.of(2019, 12, 24), earlyDay.getDate());
        assertEquals("Market closes early at 13:00", earlyDay.getDescription());
        assertFalse(earlyDay.isHoliday());
        assertTrue(earlyDay.isEarlyClose());
        assertFalse(earlyDay.isWeekend());
    }

    @Test
    public void getDaysInYear() throws IOException {
        List<TradingDay> days = tradingDayService.getTradingDays(2019);
        Set<TradingDay> holidays = tradingDayService.getHolidays(days);
        assertEquals(9, holidays.size());
        Set<TradingDay> earlyCloseDays = tradingDayService.getEarlyCloseDays(days);
        assertEquals(3, earlyCloseDays.size());
    }

    @Test
    public void getDaysIn2023() throws IOException {
        List<TradingDay> days = tradingDayService.getTradingDays(2023);
        Set<TradingDay> holidays = tradingDayService.getHolidays(days);
        assertEquals(10, holidays.size()); //NOTE: missing Juneteenth
        System.out.println("Early close");
        Set<TradingDay> early = tradingDayService.getEarlyCloseDays(days);
        assertEquals(2, early.size());

    }
}
