package com.tecacet.finance.service.stock.yahoo;

import com.tecacet.finance.model.StandardPeriodType;
import com.tecacet.finance.service.stock.DividendService;
import com.tecacet.finance.service.stock.StockServiceException;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class YahooDividendService extends AbstractYahooService implements DividendService {

    private final YahooDividendParser parser = new YahooDividendParser();

    @Override
    public Map<LocalDate, BigDecimal> getHistoricalDividends(String symbol, LocalDate fromDate, LocalDate toDate) throws StockServiceException {
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
