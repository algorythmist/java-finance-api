package com.tecacet.finance.model.calendar;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
public class TradingCalendar {

    private final int month;
    private final int year;
    @ToString.Exclude
    private final List<TradingDay> tradingDays;

}
