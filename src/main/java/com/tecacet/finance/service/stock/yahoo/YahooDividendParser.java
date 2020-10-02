package com.tecacet.finance.service.stock.yahoo;

import com.tecacet.jflat.CSVReader;
import com.tecacet.jflat.RowRecord;

import org.apache.commons.csv.CSVFormat;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class YahooDividendParser {

    public Map<LocalDate, BigDecimal> parse(InputStream is) throws IOException {
        Map<LocalDate, BigDecimal> dividends = new TreeMap<>();
        CSVReader<String[]> csvReader = CSVReader
                .createDefaultReader()
                .withFormat(CSVFormat.DEFAULT.withFirstRecordAsHeader().withSkipHeaderRecord());
        csvReader.read(is, (row, bean) -> parse(dividends, row));
        return dividends;
    }

    private void parse(Map<LocalDate, BigDecimal> dividends, RowRecord row) {
        LocalDate date = LocalDate.parse(row.get(0).trim());
        double value = Double.parseDouble(row.get(1).trim());
        dividends.put(date, BigDecimal.valueOf(value));
    }
}
