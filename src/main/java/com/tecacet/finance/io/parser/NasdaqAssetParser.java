package com.tecacet.finance.io.parser;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.finance.model.Exchange;
import com.tecacet.jflat.CSVReader;
import org.apache.commons.csv.CSVFormat;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class NasdaqAssetParser {

    private final CSVReader<Asset> reader = CSVReader.
            readerWithHeaderMapping(Asset.class, new String[]{"Symbol", "Security Name", "Round Lot Size"},
                    new String[]{"symbol", "name", "roundLotSize"})
            .withFormat(CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter('|'));

    public List<Asset> parse(InputStream is) throws IOException {
        List<Asset> assets =
                reader.readAllWithCallback(is, (row, asset) -> {
                    asset.setAssetType(AssetType.STOCK);
                    asset.setExchange(Exchange.NASDAQ);
                    return asset;
                });

        // The last one is a footer
        assets.remove(assets.size() - 1);
        return assets;
    }
}
