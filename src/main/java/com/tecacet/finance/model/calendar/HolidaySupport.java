package com.tecacet.finance.model.calendar;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class HolidaySupport {

    private Country country;
    private List<String> holidayTypes;
    private List<String> regions;
    private LocalDate fromDate;
    private LocalDate toDate;
}
