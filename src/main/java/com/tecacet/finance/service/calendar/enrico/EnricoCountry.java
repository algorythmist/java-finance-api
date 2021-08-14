package com.tecacet.finance.service.calendar.enrico;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class CountryDate {
    int day;
    int month;
    int year;
}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class EnricoCountry {

    private String countryCode;
    private String fullName;
    private List<String> holidayTypes;
    private List<String> regions;
    private CountryDate fromDate;
    private CountryDate toDate;

    public String getCountryCode() {
        return countryCode;
    }

    public String getFullName() {
        return fullName;
    }

    public List<String> getHolidayTypes() {
        return holidayTypes;
    }

    public List<String> getRegions() {
        return regions;
    }

    public LocalDate getFromDate() {
        return fromDate == null ? null : LocalDate.of(fromDate.year, fromDate.month, fromDate.day);
    }

    public LocalDate getToDate() {
        return toDate == null ? null : LocalDate.of(toDate.year, toDate.month, toDate.day);
    }
}
