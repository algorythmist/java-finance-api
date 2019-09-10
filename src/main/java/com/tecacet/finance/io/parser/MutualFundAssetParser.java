package com.tecacet.finance.io.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.csv.CSVFormat;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.jflat.CSVReader;

public class MutualFundAssetParser {

	public List<Asset> parse(InputStream is) throws IOException {
		CSVReader<Asset> reader = CSVReader.createWithHeaderMapping(Asset.class,
				new String[] { "Fund Symbol", "Fund Name", "Type" }, new String[] { "symbol", "name", "assetType" })
				.withFormat(CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter('|').withQuote(null))
				.registerConverter(AssetType.class, s -> AssetType.MUTUAL_FUND);
		return reader.readAll(is);
	}
}
