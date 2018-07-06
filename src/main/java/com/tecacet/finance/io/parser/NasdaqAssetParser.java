package com.tecacet.finance.io.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.finance.model.Exchange;
import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.CSVFileFormat;
import com.tecacet.jflat8.RowRecord;
import com.tecacet.jflat8.impl.CSVFlatFileReader;
import com.tecacet.jflat8.impl.HeaderBeanMapper;


public class NasdaqAssetParser {

	public List<Asset> parse(InputStream is) throws IOException {
		BeanMapper<Asset> beanMapper = new HeaderBeanMapper<>(Asset.class, 
				new String[] { "Symbol", "Security Name", "Round Lot Size" }, 
				new String[] { "symbol", "name", "roundLotSize" });
		CSVFileFormat fileFormat = new CSVFileFormat(CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter('|'));
		CSVFlatFileReader<Asset> reader = new CSVFlatFileReader<>(beanMapper, fileFormat);
		
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
