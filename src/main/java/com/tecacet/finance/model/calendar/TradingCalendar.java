package com.tecacet.finance.model.calendar;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class TradingCalendar {

    private final int month;
    private final int year;
    private final List<TradingDay> tradingDays;

}
