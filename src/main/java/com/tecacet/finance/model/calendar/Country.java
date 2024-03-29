package com.tecacet.finance.model.calendar;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Country {

    @EqualsAndHashCode.Include
    private final String countryCode;
    private final String countryName;
}
