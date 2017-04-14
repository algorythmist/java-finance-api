package com.tecacet.finance.service.asset;

import java.io.IOException;
import java.util.Set;

import com.tecacet.finance.model.Asset;

public interface AssetService {

	Set<Asset> getAssets() throws IOException;

}