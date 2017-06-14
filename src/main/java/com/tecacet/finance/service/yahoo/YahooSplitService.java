package com.tecacet.finance.service.yahoo;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.tecacet.finance.model.Split;
import com.tecacet.finance.model.StandardPeriodType;
import com.tecacet.finance.service.SplitService;
import com.tecacet.finance.service.StockServiceException;

public class YahooSplitService extends AbstractYahooService implements SplitService {

	private YahooSplitParser parser = new YahooSplitParser();
	
	@Override
	public List<Split> getSplitHistory(String symbol, LocalDate fromDate, LocalDate toDate)
			throws StockServiceException {
		try {
			Map<String, String> params = getRequestParams(fromDate, toDate, StandardPeriodType.DAY);
			params.put("events", "split");
			InputStream is = getUrlStream(symbol, params);
			return parser.parse(is);
		} catch (Exception e) {
			throw new StockServiceException(e);
		}
	}

}
