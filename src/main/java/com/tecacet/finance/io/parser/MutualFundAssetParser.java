package com.tecacet.finance.io.parser;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.jflat.CSVReader;
import org.apache.commons.csv.CSVFormat;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Parse Barchart File to extract mutual fund information
 */
public class MutualFundAssetParser {

    private final CSVReader<Asset> reader = CSVReader
            .readerWithHeaderMapping(Asset.class, new String[] {"Fund Symbol", "Fund Name", "Type"}, new String[] {"symbol", "name", "assetType"})
            .withFormat(CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter('|').withQuote(null))
            .registerConverter(AssetType.class, s -> AssetType.MUTUAL_FUND);

    public List<Asset> parse(InputStream is) throws IOException {
        return reader.readAll(is);
    }
}
