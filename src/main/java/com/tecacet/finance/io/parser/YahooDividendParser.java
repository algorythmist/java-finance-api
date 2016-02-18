package com.tecacet.finance.io.parser;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import com.tecacet.jflat.CSVReader;
import com.tecacet.jflat.DefaultCSVReader;

public class YahooDividendParser {

    public Map<LocalDate, Double> readDividends(InputStream is) throws IOException {
        Map<LocalDate, Double> dividends = new TreeMap<>();
        CSVReader<String[]> csvReader = new DefaultCSVReader();
        csvReader.skipHeader();
        csvReader.readWithCallback(is, (i, tokens, bean) -> parse(dividends, tokens));
        return dividends;
    }

    private void parse(Map<LocalDate, Double> dividends, String[] tokens) {
        LocalDate date = LocalDate.parse(tokens[0].trim());
        double value = Double.parseDouble(tokens[1].trim());
        dividends.put(date, value);
    }
}
