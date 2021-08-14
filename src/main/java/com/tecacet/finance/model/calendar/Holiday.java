package com.tecacet.finance.model.calendar;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Locale;

@Builder
@Getter
@ToString(onlyExplicitlyIncluded = true)
public class Holiday {

    @ToString.Include
    private final LocalDate date;
    private final Locale locale;
    private final String type;
    @ToString.Include
    private final String description;

}
