package com.tecacet.finance.services.holiday;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CountryHolidays {

    private String countryCode;
    private String countryFullName;
    private List<HolidayInfo> holidayName;


    public String getCountryCode() {
        return countryCode;
    }

    public String fullCountryName() {
        return countryFullName;
    }

    public List<HolidayInfo> getHolidayName() {
        return holidayName;
    }
}
