package com.tecacet.finance.service.yahoo;

import com.tecacet.finance.model.Split;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class YahooSplitParser {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Split> parse(InputStream is) throws IOException {
        List<Split> splits = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()));
        String line = br.readLine(); //skip header
        while ((line = br.readLine()) != null) {
            splits.add(parse(line));
        }
        br.close();
        return splits;
    }

    public Split parse(String line) {
        String[] tokens = line.split(",");
        LocalDate date = LocalDate.parse(tokens[0].trim(), formatter);
        String[] splitString = tokens[1].trim().split("/");
        return new Split(date, Integer.parseInt(splitString[1]), Integer.parseInt(splitString[0]));
    }
}
