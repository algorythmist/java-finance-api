package com.tecacet.finance.io.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.jflat.CSVReader;
import com.tecacet.jflat.FlatFileReaderCallback;
import com.tecacet.jflat.RowRecord;

public class BarchartAssetParser {

    public List<Asset> parse(InputStream is, AssetType assetType) throws IOException {
        CSVReader<Asset> reader = CSVReader.createWithIndexMapping(Asset.class, new String[]{"symbol", "name"})
               .withFormat(CSVFormat.DEFAULT.withDelimiter(':'));
        List<Asset> assets = new ArrayList<>();
        FlatFileReaderCallback<Asset> callback = (record, asset) -> {
            asset.setAssetType(assetType);
            assets.add(asset);
        };
        reader.read(is, callback);
        return assets;
    }
}
