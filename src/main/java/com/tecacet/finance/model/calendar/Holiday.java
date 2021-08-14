package com.tecacet.finance.model.calendar;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Locale;

@Builder
@Getter
public class Holiday {

    private final LocalDate date;
    private final Locale locale;
    private final String type;
    private final String description;

}
