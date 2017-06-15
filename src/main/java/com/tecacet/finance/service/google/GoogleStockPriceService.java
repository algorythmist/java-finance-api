package com.tecacet.finance.service.google;

import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

import com.tecacet.finance.model.StandardPeriodType;
import com.tecacet.finance.model.StockPrice;
import com.tecacet.finance.service.StockPriceService;
import com.tecacet.finance.service.StockServiceException;
import com.tecacet.finance.service.WebUtil;

public class GoogleStockPriceService implements StockPriceService {

	private static final String[] PROPERTIES = new String[] { "date", "open", "high", "low", "close", "volume" };

	private static final String QUOTES_URL_TEMPLATE = "https://www.google.com/finance/historical?output=csv&q=%s&startdate=%s&enddate=%s";

	private final StockPriceParser parser = new StockPriceParser(PROPERTIES);

	public GoogleStockPriceService() {
		parser.registerDateFormat("d-MMM-yy");
	}

	@Override
	public List<StockPrice> getPriceHistory(String symbol, LocalDate fromDate, LocalDate toDate,
			StandardPeriodType periodType) throws StockServiceException {
		try {
			String urlString = String.format(QUOTES_URL_TEMPLATE, URLEncoder.encode(symbol, "UTF-8"),
					fromDate.toString(), toDate.toString());
			InputStream is = WebUtil.getResponseAsStream(urlString);
			return parser.parse(is);
		} catch (Exception e) {
			throw new StockServiceException(e);
		}
	}

}
