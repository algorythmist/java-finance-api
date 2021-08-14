package com.tecacet.finance.service.calendar.tradier;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class Days {
    @JsonProperty("day")
    List<Day> days;
}

@JsonRootName(value = "calendar")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class Calendar {

    private int month;
    private int year;
    private Days days;

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public List<Day> getDays() {
        return days == null ? null : days.days;
    }
}
