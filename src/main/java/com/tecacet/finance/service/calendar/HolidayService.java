package com.tecacet.finance.service.calendar;

import com.tecacet.finance.model.calendar.Country;
import com.tecacet.finance.model.calendar.Holiday;
import com.tecacet.finance.model.calendar.HolidaySupport;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * This service provides information about international holidayss
 */
public interface HolidayService {

    /**
     * Get supported countries and information about the supported Holidays
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

    /**
     * Get all holidays for a particular year and country
     * @param year the year
     * @param countryCode the 3-letter country code
     * @return a list of holidays
     * @throws IOException if there is a connection problem
     */
    List<Holiday> getHolidaysForYear(int year, String countryCode) throws IOException;

    /**
     * Get all holidays for a particular year, month, and country
     * @param year the year
     * @param month the month in year (1-12)
     * @param countryCode the 3-letter country code
     * @return a list of holidays
     * @throws IOException if there is a connection problem
     */
    List<Holiday> getHolidaysForMonth(int year, int month, String countryCode) throws IOException;

    /**
     * Get holidays for a country in a date range
     * @param fromDate start of date range
     * @param toDate end of date range
     * @param countryCode the 3-letter country code
     * @return a list of holidays
     * @throws IOException if there is a connection problem
     */
    List<Holiday> getHolidaysForDateRange(LocalDate fromDate, LocalDate toDate, String countryCode) throws IOException;
}
