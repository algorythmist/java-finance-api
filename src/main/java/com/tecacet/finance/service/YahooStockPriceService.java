package com.tecacet.finance.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tecacet.finance.io.parser.YahooPriceParser;
import com.tecacet.finance.model.StandardPeriodType;
import com.tecacet.finance.model.StockPrice;

public class YahooStockPriceService implements StockPriceService {

	private static final String HISTQUOTES_BASE_URL = "http://ichart.yahoo.com/table.csv?";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final YahooPriceParser parser = new YahooPriceParser();

	@Override
	public List<StockPrice> getPriceHistory(String ticker, LocalDate fromDate, LocalDate toDate,
			StandardPeriodType periodType) throws StockServiceException {

		try {
			String url = buildURL(ticker, fromDate, toDate, periodType);
			logger.info(url);
			Response response = WebUtil.getURLResponse(url);
			InputStream is = response.readEntity(InputStream.class);
			return parser.parseStockHistory(is);
		} catch (IOException | WebServiceException e) {
			throw new StockServiceException(e);
		}
	}

	private String buildURL(String ticker, LocalDate fromDate, LocalDate toDate, StandardPeriodType periodType)
			throws StockServiceException, UnsupportedEncodingException {

		StringBuilder url = new StringBuilder(HISTQUOTES_BASE_URL);
		url.append("s=" + URLEncoder.encode(ticker, "UTF-8"));
		url.append("&a=" + (fromDate.getMonthValue() - 1));
		url.append("&b=" + fromDate.getDayOfMonth());
		url.append("&c=" + fromDate.getYear());
		url.append("&d=" + (toDate.getMonthValue() - 1));
		url.append("&e=" + toDate.getDayOfMonth());
		url.append("&f=" + toDate.getYear());
		url.append(String.format("&g=%s", getPeriodCode(periodType)));
		url.append("&ignore=.csv");
		return url.toString();
	}

	private String getPeriodCode(StandardPeriodType periodType) throws StockServiceException {
		switch (periodType) {
		case DAY:
			return "d";
		case WEEK:
			return "w";
		case MONTH:
			return "m";
		case YEAR:
		default:
			throw new StockServiceException("Period not supported");
		}
	}

}
