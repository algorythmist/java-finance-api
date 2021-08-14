package com.tecacet.finance.model.calendar;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class TradingDay implements Comparable<TradingDay> {

    public enum MarketStatus {
        OPEN, CLOSED
    }

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;
    private MarketStatus marketStatus;

    public boolean isHoliday() {
        return marketStatus == MarketStatus.CLOSED && !isWeekend();
    }

    public boolean isWeekend() {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public boolean isEarlyClose() {
        return marketStatus == MarketStatus.OPEN && endTime.isBefore(LocalTime.of(16, 0));
    }

    @Override
    public int compareTo(@NotNull TradingDay o) {
        return date.compareTo(o.date);
    }
}
