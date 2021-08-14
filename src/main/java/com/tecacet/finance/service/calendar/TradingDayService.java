package com.tecacet.finance.service.calendar;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;

public interface TradingDayService {

    List<Day> getDays(int year) throws IOException;

    List<Day> getDays(int year, int month) throws IOException;

    Calendar getCalendar(int year, int month) throws IOException;

    SortedSet<Day> getHolidays(List<Day> days);

    SortedSet<Day> getEarlyCloseDays(List<Day> days);
}
