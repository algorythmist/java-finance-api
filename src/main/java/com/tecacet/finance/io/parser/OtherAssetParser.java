package com.tecacet.finance.io.parser;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.finance.model.Exchange;
import com.tecacet.jflat.CSVReader;

import org.apache.commons.csv.CSVFormat;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OtherAssetParser {

    private static final Map<String, Exchange> EXCHNAGE_MAP = new HashMap<>();

    static {
        EXCHNAGE_MAP.put("A", Exchange.NYSE_MKT);
        EXCHNAGE_MAP.put("N", Exchange.NYSE);
        EXCHNAGE_MAP.put("P", Exchange.NYSE_ARCA);
        EXCHNAGE_MAP.put("Z", Exchange.BATS);
    }

    private final CSVReader<Asset> reader = CSVReader
            .createWithHeaderMapping(Asset.class, new String[] {"ACT Symbol", "Security Name", "Round Lot Size", "ETF", "Exchange"},
                    new String[] {"symbol", "name", "roundLotSize", "assetType", "exchange"})
            .withFormat(CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter('|'))
            .registerConverter(AssetType.class, s -> isETF(s) ? AssetType.ETF : AssetType.STOCK)
            .registerConverter(Exchange.class, EXCHNAGE_MAP::get);

    public List<Asset> parse(InputStream fis) throws IOException {
        List<Asset> assets = reader.readAll(fis);
        // The last one is a footer
        assets.remove(assets.size() - 1);
        return assets;
    }

    private boolean isETF(String token) {
        return "Y".equals(token.trim());
    }
}
