package com.tecacet.finance.service.calendar.tradier;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tecacet.finance.model.calendar.TradingCalendar;
import com.tecacet.finance.model.calendar.TradingDay;
import com.tecacet.finance.service.calendar.TradingDayService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TradierTradingDayService implements TradingDayService {

    private static final String CALENDAR_ENDPOINT = "https://api.tradier.com/v1/markets/calendar";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper;

    public TradierTradingDayService() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
    }

    @Override
    public List<TradingDay> getTradingDays(int year) throws IOException {
        List<TradingDay> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            List<TradingDay> days = getTradingDays(year, i);
            list.addAll(days);
        }
        return list;
    }

    @Override
    public List<TradingDay> getTradingDays(int year, int month) throws IOException {
        return getCalendar(year, month).getTradingDays();
    }

    @Override
    public TradingCalendar getCalendar(int year, int month) throws IOException {
        String url = String.format("%s?year=%d&month=%d", CALENDAR_ENDPOINT, year, month);
        logger.info("Calling: {}", url);
        String content = execute(url);
        return toTradingCalendar(objectMapper.readValue(content, Calendar.class));
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

    private TradingDay toTradingDay(Day day) {
        return TradingDay.builder()
                .date(day.getDate())
                .description(day.getDescription())
                .startTime(day.getStartTime())
                .endTime(day.getEndTime())
                .marketStatus(day.getMarketStatus())
                .build();
    }

    private TradingCalendar toTradingCalendar(Calendar calendar) {
        return new TradingCalendar(calendar.getYear(), calendar.getMonth(),
                calendar.getDays().stream().map(this::toTradingDay).collect(Collectors.toList()));
    }
}
