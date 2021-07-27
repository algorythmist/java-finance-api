package com.tecacet.finance.io.parser;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.jflat.CSVReader;
import com.tecacet.jflat.RowRecord;


import org.apache.commons.csv.CSVFormat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BarchartAssetParser {

    private final CSVReader<Asset> reader = CSVReader.readerWithIndexMapping(Asset.class, new String[] {"symbol", "name"})
            .withFormat(CSVFormat.DEFAULT.withDelimiter(':'));

    public List<Asset> parse(InputStream is, AssetType assetType) throws IOException {
        List<Asset> assets = new ArrayList<>();
        reader.read(is, (RowRecord row, Asset asset) -> {
            asset.setAssetType(assetType);
            assets.add(asset);
        });
        return assets;
    }
}
