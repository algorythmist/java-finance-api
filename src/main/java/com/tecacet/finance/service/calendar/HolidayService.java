package com.tecacet.finance.service.calendar;

import com.tecacet.finance.model.calendar.Holiday;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface HolidayService {

    List<Country> getSupportedCountries() throws IOException;

    List<CountryHolidays> whereIsPublicHoliday(LocalDate date) throws IOException;

    List<Holiday> getHolidaysForYear(int year, String countryCode) throws IOException;

    List<Holiday> getHolidaysForMonth(int year, int month, String countryCode) throws IOException;

    List<Holiday> getHolidaysForDateRange(LocalDate fromDate, LocalDate toDate, String countryCode) throws IOException;
}
