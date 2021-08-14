package com.tecacet.finance.service.calendar;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecacet.finance.model.calendar.Country;
import com.tecacet.finance.model.calendar.Holiday;
import com.tecacet.finance.model.calendar.HolidaySupport;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnricoHolidayService implements HolidayService {

    private static final String ENDPOINT = "https://kayaposoft.com/enrico/json/v2.0";

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<HolidaySupport> getSupportedCountries() throws IOException {
        HttpUrl httpUrl = HttpUrl.parse(ENDPOINT).newBuilder()
                .addQueryParameter("action", "getSupportedCountries")
                .build();
        String content = execute(httpUrl);
        List<EnricoCountry> enricoCountries =
                objectMapper.readValue(content, new TypeReference<List<EnricoCountry>>() {
                });
        return enricoCountries.stream().map(this::toHolidaySupport).collect(Collectors.toList());
    }

    @Override
    public Map<Country, List<Holiday>> whereIsPublicHoliday(LocalDate date) throws IOException {
        HttpUrl httpUrl = HttpUrl.parse(ENDPOINT).newBuilder()
                .addQueryParameter("action", "whereIsPublicHoliday")
                .addQueryParameter("date", date.format(dateTimeFormatter))
                .build();
        String content = execute(httpUrl);
        List<CountryHolidays> countryHolidays =
                objectMapper.readValue(content, new TypeReference<List<CountryHolidays>>() {
                });
        return countryHolidays.stream().collect(Collectors.toMap(
                ch -> new Country(ch.getCountryCode(), ch.getCountryName()),
                ch -> toPublicHolidays(ch, date)
        ));
    }

    @Override
    public List<Holiday> getHolidaysForYear(int year, String countryCode) throws IOException {

        HttpUrl httpUrl = HttpUrl.parse(ENDPOINT).newBuilder()
                .addQueryParameter("action", "getHolidaysForYear")
                .addQueryParameter("year", Integer.toString(year))
                .addQueryParameter("country", countryCode)
                .build();
        String content = execute(httpUrl);
        List<EnricoHoliday> holidays = objectMapper.readValue(content, new TypeReference<List<EnricoHoliday>>() {
        });
        return holidays.stream().map(this::toHoliday).collect(Collectors.toList());
    }

    @Override
    public List<Holiday> getHolidaysForMonth(int year, int month, String countryCode) throws IOException {

        HttpUrl httpUrl = HttpUrl.parse(ENDPOINT).newBuilder()
                .addQueryParameter("action", "getHolidaysForMonth")
                .addQueryParameter("month", Integer.toString(month))
                .addQueryParameter("year", Integer.toString(year))
                .addQueryParameter("country", countryCode)
                .build();
        String content = execute(httpUrl);
        List<EnricoHoliday> holidays = objectMapper.readValue(content, new TypeReference<List<EnricoHoliday>>() {
        });
        return holidays.stream().map(this::toHoliday).collect(Collectors.toList());
    }

    @Override
    public List<Holiday> getHolidaysForDateRange(LocalDate fromDate, LocalDate toDate, String countryCode) throws IOException {
        HttpUrl httpUrl = HttpUrl.parse(ENDPOINT).newBuilder()
                .addQueryParameter("action", "getHolidaysForDateRange")
                .addQueryParameter("fromDate", fromDate.format(dateTimeFormatter))
                .addQueryParameter("toDate", toDate.format(dateTimeFormatter))
                .addQueryParameter("country", countryCode)
                .build();
        String content = execute(httpUrl);
        List<EnricoHoliday> holidays = objectMapper.readValue(content, new TypeReference<List<EnricoHoliday>>() {
        });
        return holidays.stream().map(this::toHoliday).collect(Collectors.toList());
    }

    private String execute(HttpUrl httpUrl) throws IOException {
        String url = httpUrl.toString();
        logger.info("Executing request: {}", url);
        Request request = new Request.Builder().url(url).build();
        Response response = httpClient.newCall(request).execute();
        ResponseBody responseBody = response.body();
        String content = responseBody == null ? "" : responseBody.string();
        logger.debug("Response received: {}", content);
        if (!response.isSuccessful()) {
            throw new IOException(String.format("Call failed with code %d and message: %s", response.code(), content));
        }
        //TODO: detect error: {"error":"Dates before 1 Jan 2011 are not supported"}
        return content;
    }

    private List<Holiday> toPublicHolidays(CountryHolidays countryHolidays, LocalDate date) {
        return countryHolidays.getHolidays().stream().map(h -> toPublicHoliday(h, date))
                .collect(Collectors.toList());
    }

    private Holiday toPublicHoliday(HolidayInfo holidayInfo, LocalDate date) {
        return Holiday.builder()
                .date(date)
                .type("public_holiday")
                .locale(new Locale(holidayInfo.lang))
                .description(holidayInfo.text)
                .build();
    }

    private Holiday toHoliday(EnricoHoliday enricoHoliday) {
        Optional<HolidayInfo> holidayInfoOptional = enricoHoliday.findEnglishOrAny();
        Holiday.HolidayBuilder builder = Holiday.builder()
                .date(enricoHoliday.getDate())
                .type(enricoHoliday.getHolidayType());
        holidayInfoOptional.ifPresent(info -> {
            builder.locale(new Locale(info.lang));
            builder.description(info.text);
        });
        return builder.build();
    }

    private HolidaySupport toHolidaySupport(EnricoCountry enricoCountry) {
        return HolidaySupport.builder()
                .country(new Country(enricoCountry.getCountryCode(), enricoCountry.getFullName()))
                .fromDate(enricoCountry.getFromDate())
                .toDate(enricoCountry.getToDate())
                .holidayTypes(enricoCountry.getHolidayTypes())
                .regions(enricoCountry.getRegions())
                .build();
    }
}
