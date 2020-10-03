package com.tecacet.finance.services.holiday;


import static org.junit.Assert.assertEquals;

import org.junit.Test;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class EnricoHolidayServiceTest  {

    EnricoHolidayService holidayService = new EnricoHolidayService();

    @Test
    public void getSupportedCountries() throws IOException {
        List<Country> countries = holidayService.getSupportedCountries();
        assertEquals(51, countries.size());
        Country country = countries.get(1);
        assertEquals("aus", country.getCountryCode());
        assertEquals("Australia", country.getFullName());
        assertEquals("[public_holiday, school_holiday, other_day]",
                country.getHolidayTypes().toString());
        assertEquals("[act, qld, nsw, nt, sa, tas, vic, wa]",
                country.getRegions().toString());
        assertEquals(LocalDate.of(2011,1 ,1), country.getFromDate());
        assertEquals(LocalDate.of(32767,12 ,31), country.getToDate());
    }

    @Test
    public void whereIsPublicHoliday() throws IOException {
        List<CountryHolidays> countries =
                holidayService.whereIsPublicHoliday(LocalDate.of(2025, 7, 5));
        assertEquals(3, countries.size());
    }

    @Test
    public void getHolidaysForYear() throws IOException {


        List<Holiday> holidays = holidayService.getHolidaysForYear(2015, "usa");
        assertEquals(10, holidays.size());

        Holiday holiday = holidays.get(2);
        assertEquals("postal_holiday", holiday.getHolidayType());
        assertEquals(LocalDate.of(2015, 2, 16), holiday.getDate());
    }

    @Test
    public void getHolidaysForDateRange() throws IOException {
        List<Holiday> holidays = holidayService.getHolidaysForDateRange(
                LocalDate.of(2022, 7, 4),
                LocalDate.of(2024, 7, 4),
                "usa"
        );
        assertEquals(21, holidays.size());
    }
}