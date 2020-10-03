package com.tecacet.finance.services.holiday;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class HolidayInfo {
    String lang;
    String text;
}
