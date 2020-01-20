package com.tecacet.finance.service.asset;

import com.tecacet.finance.model.Asset;

import java.io.IOException;
import java.util.Set;

public interface AssetService {

    Set<Asset> getAssets() throws IOException;

}