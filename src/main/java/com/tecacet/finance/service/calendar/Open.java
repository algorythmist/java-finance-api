package com.tecacet.finance.service.calendar;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "open")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class Open {

    String start;
    String end;

    boolean isEarlyClose() {
        return end.compareTo("16:00") < 0;
    }

}
