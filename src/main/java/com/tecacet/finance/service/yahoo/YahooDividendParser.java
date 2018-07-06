package com.tecacet.finance.service.yahoo;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import com.tecacet.jflat8.CSVFileFormat;
import com.tecacet.jflat8.RowRecord;
import com.tecacet.jflat8.impl.CSVFlatFileReader;
import com.tecacet.jflat8.impl.DefaultCSVReader;

public class YahooDividendParser {

    public Map<LocalDate, BigDecimal> parse(InputStream is) throws IOException {
        Map<LocalDate, BigDecimal> dividends = new TreeMap<>();
        CSVFlatFileReader<String[]> csvReader = new DefaultCSVReader((CSVFileFormat)CSVFileFormat.defaultFormat().skipHeader());
        csvReader.read(is, (row, bean) -> parse(dividends, row));
        return dividends;
    }

    private void parse(Map<LocalDate, BigDecimal> dividends, RowRecord row) {
        LocalDate date = LocalDate.parse(row.get(0).trim());
        double value = Double.parseDouble(row.get(1).trim());
        dividends.put(date, BigDecimal.valueOf(value));
    }
}
