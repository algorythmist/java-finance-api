package com.tecacet.finance.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Asset {

    private String symbol;
    private String name;
    private AssetType assetType;
    private int roundLotSize;
    private Exchange exchange;

}
