package com.tecacet.finance.services.tradingday;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TradierTradingDayService {

    private static final String CALENDAR_ENDPOINT = "https://api.tradier.com/v1/markets/calendar";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper;

    private final int startYear = 2018;

    public TradierTradingDayService() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
    }

    public List<Day> getDays(int year) throws IOException {
        List<Day> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            List<Day> days = getDays(year, i);
            list.addAll(days);
        }
        return list;
    }

    public List<Day> getDays(int year, int month) throws IOException {
        return getCalendar(year, month).getDays();
    }

    public Calendar getCalendar(int year, int month) throws IOException {
        String url = String.format("%s?year=%d&month=%d", CALENDAR_ENDPOINT, year, month);
        logger.info("Calling: {}", url);
        String content = execute(url);
        return objectMapper.readValue(content, Calendar.class);
    }

    private String execute(String url) throws IOException {
        Request request = new Request.Builder().url(url)
                .addHeader("Accept", "application/json")
                .build();
        Response response = httpClient.newCall(request).execute();
        ResponseBody responseBody = response.body();
        String content = responseBody == null ? "" : responseBody.string();
        logger.debug("Response received: {}", content);
        if (!response.isSuccessful()) {
            throw new IOException(String.format("Call failed with code %d and message: %s", response.code(), content));
        }
        return content;
    }

    SortedSet<Day> getHolidays(List<Day> days) {
        return days.stream().filter(Day::isHoliday).collect(Collectors.toCollection(TreeSet::new));
    }

    SortedSet<Day> getEarlyCloseDays(List<Day> days) {
        return days.stream().filter(Day::isEarlyClose).collect(Collectors.toCollection(TreeSet::new));
    }
}
