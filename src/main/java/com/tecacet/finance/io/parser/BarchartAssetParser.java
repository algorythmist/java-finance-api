package com.tecacet.finance.io.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.CSVFileFormat;
import com.tecacet.jflat8.RowRecord;
import com.tecacet.jflat8.impl.CSVFlatFileReader;
import com.tecacet.jflat8.impl.IndexBeanMapper;

public class BarchartAssetParser {
	
	public List<Asset> parse(InputStream is, AssetType assetType) throws IOException {
		BeanMapper<Asset> beanMapper = new IndexBeanMapper<>(Asset.class, new String[] {"symbol", "name"});
		CSVFileFormat fileFormat = new CSVFileFormat(CSVFormat.DEFAULT.withDelimiter(':'));
		CSVFlatFileReader<Asset> reader = new CSVFlatFileReader<>(beanMapper, fileFormat);
		List<Asset> assets = new ArrayList<>();
		reader.read(is, (RowRecord row, Asset asset) -> {
			asset.setAssetType(assetType);
			assets.add(asset);
		});
		return assets;
	}
}
