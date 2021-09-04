package com.tecacet.finance.service.calendar;

import com.tecacet.finance.model.calendar.Country;
import com.tecacet.finance.model.calendar.Holiday;
import com.tecacet.finance.model.calendar.HolidaySupport;
import com.tecacet.finance.service.calendar.enrico.EnricoHolidayService;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EnricoHolidayServiceTest {

    private final HolidayService holidayService = new EnricoHolidayService();

    @Test
    public void getSupportedCountries() throws IOException {
        List<HolidaySupport> countries = holidayService.getSupportedCountries();
        assertEquals(53, countries.size());
        HolidaySupport holidaySupport = countries.get(1);
        Country country = holidaySupport.getCountry();
        assertEquals("aus", country.getCountryCode());
        assertEquals("Australia", country.getCountryName());
        assertEquals("[public_holiday, school_holiday, other_day]",
                holidaySupport.getHolidayTypes().toString());
        assertEquals("[act, qld, nsw, nt, sa, tas, vic, wa]",
                holidaySupport.getRegions().toString());
        assertEquals(LocalDate.of(2011, 1, 1), holidaySupport.getFromDate());
        assertEquals(LocalDate.of(32767, 12, 31), holidaySupport.getToDate());
    }

    @Test
    public void whereIsPublicHoliday() throws IOException {
        Map<Country, List<Holiday>> countries =
                holidayService.whereIsPublicHoliday(LocalDate.of(2025, 7, 5));
        assertEquals(3, countries.size());
        System.out.println(countries);
        //Hash code is only on country code
        List<Holiday> countryHolidays = countries.get(new Country("cze", "anything"));
        //TODO: both of these represent Cyril and Methodius Day in different locales. Should I keep only English?
        assertEquals(2, countryHolidays.size());
    }

    @Test
    public void getHolidaysForYear() throws IOException {

        List<Holiday> holidays = holidayService.getHolidaysForYear(2015, "usa");
        assertEquals(10, holidays.size());

        Holiday holiday = holidays.get(2);
        assertEquals("postal_holiday", holiday.getType());
        assertEquals(LocalDate.of(2015, 2, 16), holiday.getDate());
        assertEquals("Presidents' Day", holiday.getDescription());
        assertEquals(Locale.ENGLISH, holiday.getLocale());
    }

    @Test
    public void getHolidaysForMonth() throws IOException {

        List<Holiday> holidays = holidayService.getHolidaysForMonth(2020, 11, "USA");
        assertEquals(2, holidays.size());

        Holiday holiday = holidays.get(1);
        assertEquals("postal_holiday", holiday.getType());
        assertEquals("Thanksgiving Day", holiday.getDescription());
        assertEquals(Locale.ENGLISH, holiday.getLocale());
        assertEquals(LocalDate.of(2020, 11, 26), holiday.getDate());
    }

    @Test(expected = IOException.class)
    public void getHolidaysForInvalidMonth() throws IOException {
        holidayService.getHolidaysForMonth(2020, 0, "USA");
    }

    @Test
    public void getHolidaysForDateRange() throws IOException {
        List<Holiday> holidays = holidayService.getHolidaysForDateRange(
                LocalDate.of(2022, 7, 4),
                LocalDate.of(2024, 7, 4),
                "usa"
        );
        assertEquals(21, holidays.size());
        Holiday holiday = holidays.get(0);
        assertEquals("postal_holiday", holiday.getType());
    }
}
