package com.tecacet.finance.service.calendar;

import com.tecacet.finance.model.calendar.TradingCalendar;
import com.tecacet.finance.model.calendar.TradingDay;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public interface TradingDayService {

    List<TradingDay> getTradingDays(int year) throws IOException;

    List<TradingDay> getTradingDays(int year, int month) throws IOException;

    TradingCalendar getCalendar(int year, int month) throws IOException;

    default SortedSet<TradingDay> getHolidays(List<TradingDay> days) {
        return days.stream().filter(TradingDay::isHoliday).collect(Collectors.toCollection(TreeSet::new));
    }

    default SortedSet<TradingDay> getEarlyCloseDays(List<TradingDay> days) {
        return days.stream().filter(TradingDay::isEarlyClose).collect(Collectors.toCollection(TreeSet::new));
    }
}
