package com.tecacet.finance.service.calendar;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class HolidayInfo {
    String lang;
    String text;
}
