package com.tecacet.finance.service.yahoo;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Map;

import com.tecacet.finance.model.StandardPeriodType;
import com.tecacet.finance.service.DividendService;
import com.tecacet.finance.service.StockServiceException;

public class YahooDividendService extends AbstractYahooService implements DividendService {

	private final YahooDividendParser parser = new YahooDividendParser();
	
	@Override
	public Map<LocalDate, Double> getHistoricalDividends(String symbol, LocalDate fromDate, LocalDate toDate)
			throws StockServiceException {
		try {
			Map<String, String> params = getRequestParams(fromDate, toDate, StandardPeriodType.DAY);
			params.put("events", "div");
			InputStream is = getUrlStream(symbol, params);
			return parser.parse(is);
		} catch (Exception e) {
			throw new StockServiceException(e);
		}
	}

}
