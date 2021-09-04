package com.tecacet.finance.service.asset;

import com.tecacet.finance.model.Asset;

import java.io.IOException;
import java.util.Set;

/**
 * Get the list of assets traded in various exchanges
 */
public interface AssetService {

    /**
     * Get all traded assets
     * @return all assets
     * @throws IOException if there is a problem with the connection
     */
    Set<Asset> getAssets() throws IOException;

}