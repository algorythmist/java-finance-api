package com.tecacet.finance.service.calendar.enrico;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class HolidayInfo {
    String lang;
    String text;
}
