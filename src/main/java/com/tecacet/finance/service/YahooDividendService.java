package com.tecacet.finance.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tecacet.finance.io.parser.YahooDividendParser;

public class YahooDividendService implements DividendService {

	private static final String DIVIDENDS_BASE_URL = "http://ichart.finance.yahoo.com/table.csv?";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final YahooDividendParser dividendParser;

	public YahooDividendService() {
		this.dividendParser = new YahooDividendParser();
	}

	@Override
	public Map<LocalDate, Double> getHistoricalDividends(String ticker, LocalDate fromDate, LocalDate toDate)
			throws StockServiceException {
		try {
			String url = buildDividendURL(ticker, fromDate, toDate);
			logger.info(url);
			Response response = WebUtil.getURLResponse(url);
			InputStream is = response.readEntity(InputStream.class);
			return dividendParser.readDividends(is);
		} catch (IOException | WebServiceException e) {
			throw new StockServiceException(e);
		}
	}

	private String buildDividendURL(String ticker, LocalDate fromDate, LocalDate toDate)
			throws UnsupportedEncodingException {
		StringBuilder url = new StringBuilder(DIVIDENDS_BASE_URL);
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
