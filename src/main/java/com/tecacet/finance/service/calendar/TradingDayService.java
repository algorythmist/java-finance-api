package com.tecacet.finance.service.calendar;

import com.tecacet.finance.model.calendar.TradingCalendar;
import com.tecacet.finance.model.calendar.TradingDay;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * This service retrieves the US Stock market trading days
 */
public interface TradingDayService {

    /**
     * Get all trading days in a year
     * @param year the year
     * @return a list of trading days
     * @throws IOException if there is a connection issue
     */
    List<TradingDay> getTradingDays(int year) throws IOException;

    /**
     * Get all trading days in a month
     * @param year the year
     * @param month the month number (1-12)
     * @return a list of trading days
     * @throws IOException if there is a connection issue
     */
    List<TradingDay> getTradingDays(int year, int month) throws IOException;

    TradingCalendar getCalendar(int year, int month) throws IOException;

    /**
     * Given a list of trading days, identify the holidays
     * @param days a list of trading days
     * @return a collection of holidays ordered by date
     */
    default SortedSet<TradingDay> getHolidays(List<TradingDay> days) {
        return days.stream().filter(TradingDay::isHoliday).collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Given a list of trading days, identify the days when the market closes early
     * @param days a list of trading days
     * @return a collection of early close days ordered by date
     */
    default SortedSet<TradingDay> getEarlyCloseDays(List<TradingDay> days) {
        return days.stream().filter(TradingDay::isEarlyClose).collect(Collectors.toCollection(TreeSet::new));
    }
}
