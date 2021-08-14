package com.tecacet.finance.service.calendar;

import com.tecacet.finance.model.calendar.Country;
import com.tecacet.finance.model.calendar.Holiday;
import com.tecacet.finance.model.calendar.HolidaySupport;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface HolidayService {

    /**
     * Get supported countries and information about the spported Holidays
     * @return a list of supported countries
     * @throws IOException if API call fails
     */
    List<HolidaySupport> getSupportedCountries() throws IOException;

    /**
     * Gives the list of countries where the date is a public holiday,
     * along with information about the holidays on this date
     * @param date a date
     * @return a map keyed by country containing the holidays on the date
     * @throws IOException if API call fails
     */
    Map<Country, List<Holiday>> whereIsPublicHoliday(LocalDate date) throws IOException;

    List<Holiday> getHolidaysForYear(int year, String countryCode) throws IOException;

    List<Holiday> getHolidaysForMonth(int year, int month, String countryCode) throws IOException;

    List<Holiday> getHolidaysForDateRange(LocalDate fromDate, LocalDate toDate, String countryCode) throws IOException;
}
