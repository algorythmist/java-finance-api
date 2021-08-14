package com.tecacet.finance.service.calendar.enrico;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class HolidayDate {
    int day;
    int month;
    int year;
    int dayOfWeek;

    public LocalDate toLocalDate() {
        return LocalDate.of(year, month, day);
    }
}


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EnricoHoliday {

    private HolidayDate date;
    private String holidayType;
    @JsonProperty("name")
    private List<HolidayInfo> holidayInfoList;

    public LocalDate getDate() {
        return LocalDate.of(date.year, date.month, date.day);
    }

    public String getHolidayType() {
        return holidayType;
    }

    public Optional<HolidayInfo> findEnglishOrAny() {
        if (holidayInfoList == null || holidayInfoList.isEmpty()) {
            return Optional.empty();
        }
        Optional<HolidayInfo> englishHoliday = holidayInfoList.stream().filter(h -> h.lang.equals("en")).findFirst();
        if (englishHoliday.isPresent()) {
            return englishHoliday;
        }
        return holidayInfoList.stream().findFirst();
    }

}
