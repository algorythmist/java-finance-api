package com.tecacet.finance.service.stock.yahoo;

import com.tecacet.finance.model.StandardPeriodType;
import com.tecacet.finance.service.stock.StockServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractYahooService {


    private static final String HISTQUOTES2_BASE_URL = "https://query1.finance.yahoo.com/v7/finance/download/";
    private static final int CONNECTION_TIMEOUT = 10000;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Map<String, String> getRequestParams(LocalDate from, LocalDate to, StandardPeriodType periodType) throws StockServiceException {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("period1", String.valueOf(toSeconds(from)));
        params.put("period2", String.valueOf(toSeconds(to)));
        params.put("interval", getPeriodCode(periodType));
        // crumb
        //params.put("crumb", CrumbManager.getCrumb());
        return params;
    }

    protected InputStream getUrlStream(String symbol, Map<String, String> params) throws IOException {
        String url = HISTQUOTES2_BASE_URL + URLEncoder.encode(symbol, StandardCharsets.UTF_8) + "?" + getURLParameters(params);

        // Get CSV from Yahoo
        logger.info("Sending request: {}", url);

        URL request = new URL(url);
        RedirectableRequest redirectableRequest = new RedirectableRequest(request, 5);
        redirectableRequest.setConnectTimeout(CONNECTION_TIMEOUT);
        redirectableRequest.setReadTimeout(CONNECTION_TIMEOUT);
        Map<String, String> requestProperties = new HashMap<>();
        //requestProperties.put("Cookie", CrumbManager.getCookie());
        URLConnection connection = redirectableRequest.openConnection(requestProperties);
        return connection.getInputStream();
    }

    private String getURLParameters(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> entry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            String key = entry.getKey();
            String value = entry.getValue();
            key = URLEncoder.encode(key, StandardCharsets.UTF_8);
            value = URLEncoder.encode(value, StandardCharsets.UTF_8);
            sb.append(String.format("%s=%s", key, value));
        }
        return sb.toString();
    }

    private long toSeconds(LocalDate date) {
        ZoneId zoneId = ZoneId.of("America/New_York");
        return date.atStartOfDay(zoneId).toEpochSecond();
    }

    private static String getPeriodCode(StandardPeriodType periodType) throws StockServiceException {
        switch (periodType) {
            case DAY:
                return "1d";
            case WEEK:
                return "5d";
            case MONTH:
                return "1mo";
            case YEAR:
            default:
                throw new StockServiceException("Period type " + periodType + " not supported");
        }
    }


}
