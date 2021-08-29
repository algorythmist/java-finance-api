# JFApi

![Coverage](.../badges/jacoco.svg)

## Java Finance API

Accessing financial data can be tricky, especially of you are not willing to pay for it. 
The data sources exist, but they come in different formats, are poorly (or not at all) documented, 
and may change or disappear without warning.

Java Finance API takes a diversification approach to accessing financial data. We provide ways to access 
a bunch of freely available financial sources by writing very little code. You can pick the one that better suits your needs, and easily switch to another provider if you choose to.

## Features

- Retrieve stock prices from various sources
- Retrieve dividend and split info from various sources
- Retrieve information about traded assets from different sources
- Retrieve currency exchange rates
- other financial information available through open APIs

## Examples

Get Historical Prices from Yahoo
```java
StockPriceService stockPriceService = new YahooStockPriceService();
LocalDate fromDate = LocalDate.of(2014, 1, 2);
LocalDate toDate = LocalDate.of(2014, 10, 31);
List<StockPrice> prices = stockPriceService.getPriceHistory("AAPL", fromDate, toDate, StandardPeriodType.DAY);
```

Getting historical prices from Google, is pretty much the same code, just different implementation
```java
StockPriceService stockPriceService = new GoogleStockPriceService();
LocalDate fromDate = LocalDate.of(2014, 1, 2);
LocalDate toDate = LocalDate.of(2014, 10, 31);
List<StockPrice> prices = stockPriceService.getPriceHistory("AAPL", fromDate, toDate, StandardPeriodType.DAY);
```