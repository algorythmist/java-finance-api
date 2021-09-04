package com.tecacet.finance.service.calendar.enrico;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class CountryHolidays {

    private String countryCode;
    private String countryFullName;
    private List<HolidayInfo> holidayName;

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryFullName;
    }

    public List<HolidayInfo> getHolidays() {
        return holidayName;
    }
}
