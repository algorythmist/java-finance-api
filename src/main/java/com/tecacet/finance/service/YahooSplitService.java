package com.tecacet.finance.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tecacet.finance.model.Split;

public class YahooSplitService {

    private static final String BASE_URL = "http://ichart.yahoo.com/x?";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Split> getSplitHistory(String ticker, LocalDate fromDate, LocalDate toDate)
            throws StockServiceException {

        try {
            String url = buildURL(ticker, fromDate, toDate);
            logger.info(url);
            Response response = WebUtil.getURLResponse(url);
            InputStream is = response.readEntity(InputStream.class);
            return parse(is);
        } catch (IOException | WebServiceException e) {
            throw new StockServiceException(e);
        }
    }

    private List<Split> parse(InputStream is) throws IOException {
        List<Split> splits = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("SPLIT")) {
                String[] tokens = line.split(",");
                LocalDate date = LocalDate.parse(tokens[1].trim(), formatter);
                String[] splitString = tokens[2].trim().split(":");
                Split split = new Split(date, Integer.parseInt(splitString[0]), Integer.parseInt(splitString[1]));
                splits.add(split);
            }
        }
        br.close();
        return splits;
    }

    private String buildURL(String ticker, LocalDate fromDate, LocalDate toDate) throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder(BASE_URL);
        url.append("s=" + URLEncoder.encode(ticker, "UTF-8"));
        url.append("&a=" + (fromDate.getMonthValue() - 1));
        url.append("&b=" + fromDate.getDayOfMonth());
        url.append("&c=" + fromDate.getYear());
        url.append("&d=" + (toDate.getMonthValue() - 1));
        url.append("&e=" + toDate.getDayOfMonth());
        url.append("&f=" + toDate.getYear());
        url.append("&g=v"); // dividends
        url.append("&ignore=.csv");
        return url.toString();
    }
}
