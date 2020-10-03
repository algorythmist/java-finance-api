package com.tecacet.finance.services.holiday;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class HolidayDate {
    int day;
    int month;
    int year;
    int dayOfWeek;
}


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Holiday {

    private HolidayDate date;
    private String holidayType;
    private List<HolidayInfo> name;

    public LocalDate getDate() {
        return LocalDate.of(date.year, date.month, date.day);
    }

    public String getHolidayType() {
        return holidayType;
    }
}
