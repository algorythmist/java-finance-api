package com.tecacet.finance.services.holiday;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EnricoHolidayService {

    private static final String ENDPOINT = "https://kayaposoft.com/enrico/json/v2.0";

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Country> getSupportedCountries() throws IOException {
        HttpUrl httpUrl = HttpUrl.parse(ENDPOINT).newBuilder()
                .addQueryParameter("action", "getSupportedCountries")
                .build();
        String content = execute(httpUrl);
        return objectMapper.readValue(content, new TypeReference<List<Country>>() {
        });
    }

    public List<CountryHolidays> whereIsPublicHoliday(LocalDate date) throws IOException {
        HttpUrl httpUrl = HttpUrl.parse(ENDPOINT).newBuilder()
                .addQueryParameter("action", "whereIsPublicHoliday")
                .addQueryParameter("date", date.format(dateTimeFormatter))
                .build();
        String content = execute(httpUrl);
        return objectMapper.readValue(content, new TypeReference<List<CountryHolidays>>() {
        });
    }

    public List<Holiday> getHolidaysForYear(int year, String countryCode) throws IOException {

        HttpUrl httpUrl = HttpUrl.parse(ENDPOINT).newBuilder()
                .addQueryParameter("action", "getHolidaysForYear")
                .addQueryParameter("year", Integer.toString(year))
                .addQueryParameter("country", countryCode)
                .build();
        String content = execute(httpUrl);
        return objectMapper.readValue(content, new TypeReference<List<Holiday>>() {
        });
    }

    public List<Holiday> getHolidaysForMonth(int year, int month, String countryCode) throws IOException {

        HttpUrl httpUrl = HttpUrl.parse(ENDPOINT).newBuilder()
                .addQueryParameter("action", "getHolidaysForMonth")
                .addQueryParameter("month", Integer.toString(month))
                .addQueryParameter("year", Integer.toString(year))
                .addQueryParameter("country", countryCode)
                .build();
        String content = execute(httpUrl);
        return objectMapper.readValue(content, new TypeReference<List<Holiday>>() {
        });
    }

    public List<Holiday> getHolidaysForDateRange(LocalDate fromDate, LocalDate toDate, String countryCode) throws IOException {
        HttpUrl httpUrl = HttpUrl.parse(ENDPOINT).newBuilder()
                .addQueryParameter("action", "getHolidaysForDateRange")
                .addQueryParameter("fromDate", fromDate.format(dateTimeFormatter))
                .addQueryParameter("toDate", toDate.format(dateTimeFormatter))
                .addQueryParameter("country", countryCode)
                .build();
        String content = execute(httpUrl);
        return objectMapper.readValue(content, new TypeReference<List<Holiday>>() {
        });
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
}
