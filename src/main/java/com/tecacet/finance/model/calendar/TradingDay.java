package com.tecacet.finance.model.calendar;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Attributes of a trading day
 */
@Getter
@Builder
@ToString(onlyExplicitlyIncluded = true)
public class TradingDay implements Comparable<TradingDay> {

    public enum MarketStatus {
        OPEN, CLOSED
    }

    @ToString.Include
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    @ToString.Include
    private String description;
    @ToString.Include
    private MarketStatus marketStatus;

    /**
     * @return true if the market is closed on a weekday
     */
    public boolean isHoliday() {
        return marketStatus == MarketStatus.CLOSED && !isWeekend();
    }

    /**
     * @return true if it is Saturday or Sunday
     */
    public boolean isWeekend() {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    /**
     * @return true if it's an early close day
     */
    public boolean isEarlyClose() {
        return marketStatus == MarketStatus.OPEN && endTime.isBefore(LocalTime.of(16, 0));
    }

    @Override
    public int compareTo(@NotNull TradingDay o) {
        return date.compareTo(o.date);
    }
}
