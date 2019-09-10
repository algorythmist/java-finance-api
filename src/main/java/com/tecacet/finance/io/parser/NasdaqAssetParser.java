package com.tecacet.finance.io.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.finance.model.Exchange;
import com.tecacet.jflat.CSVReader;
import com.tecacet.jflat.RowRecord;

public class NasdaqAssetParser {

	public List<Asset> parse(InputStream is) throws IOException {

		CSVReader<Asset> reader = CSVReader.createWithHeaderMapping(Asset.class,
				new String[] { "Symbol", "Security Name", "Round Lot Size" },
				new String[] { "symbol", "name", "roundLotSize" })
				.withFormat(CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter('|').withQuote(null));
		
		List<Asset> assets = new ArrayList<>();
		
		reader.read(is, (RowRecord row, Asset asset) -> {
			asset.setAssetType(AssetType.STOCK);
			asset.setExchange(Exchange.NASDAQ);
			assets.add(asset);
		});
		
		// The last one is a footer
		assets.remove(assets.size() - 1);
		return assets;
	}
}
