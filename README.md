# Java Finance API

[![javadoc](https://javadoc.io/badge2/com.tecacet/java-finance-api/javadoc.svg)](https://javadoc.io/doc/com.tecacet/java-finance-api)
[![Maven Central](https://img.shields.io/maven-central/v/com.tecacet/java-finance-api.svg)](https://search.maven.org/search?q=a:java-finance-api)

Accessing financial data can be tricky, especially of you are not willing to pay for it. 
The data sources exist, but they come in different formats, are poorly (or not at all) documented, 
and may change or disappear without warning.

Java Finance API takes a diversification approach to accessing financial data. We provide ways to access 
a bunch of freely available financial sources by writing very little code. You can pick the one that better suits your needs, and easily switch to another provider if you choose to.

## Features

- Stock prices from Yahoo Finance
- Dividend and split history info from Yahoo Finance
- Information about traded assets from Barchart
- Currency exchange rates from Grandtrunk
- Trading calendars from Enrico and Tradier

## Yahoo Historical Prices, Dividends, and Splits

Get Historical Prices from Yahoo
```java
    StockPriceService stockPriceService = new YahooStockPriceService();
    LocalDate fromDate = LocalDate.of(2014, 1, 2);
    LocalDate toDate = LocalDate.of(2014, 11, 1);
    List<Quote> prices = stockPriceService.getPriceHistory("AAPL", fromDate, toDate, StandardPeriodType.DAY);
```
Get Historical Splits from Yahoo
```java
    SplitService splitService = new YahooSplitService();
    List<Split> splits = splitService.getSplitHistory("AAPL", LocalDate.of(2005, 1, 1), LocalDate.of(2015, 12, 31));
        
```
Get Historical Dividends from Yahoo
```java
    DividendService dividendService = new YahooDividendService();
    Map<LocalDate, BigDecimal> dividends = dividendService.getHistoricalDividends("AGG", LocalDate.of(2000, 1, 1), LocalDate.of(2016, 11, 9));
    
```

## Currency Exchange Rates

```java
    CurrencyExchangeService exchangeService = new GrandtrunkCurrencyExchangeService();
    LocalDate date = LocalDate.of(2014, 1, 12);
    // Get Historical rate
    double rate = exchangeService.getExchangeRate("USD", "GBP", date);

    //Get current rate
    double currentRate = exchangeService.getCurrentExchangeRate("USD", "GBP");

    //Get supported currencis
    List<String> currencies = exchangeService.getSupportedCurrencies();
```

## Holidays and Trading Days

Retrieve Market Trading Days

```java
    TradingDayService tradingDayService = new TradierTradingDayService();
    //Get trading days in a year
    List<TradingDay> days = tradingDayService.getTradingDays(2019);
    //Extract the holidays
    Set<TradingDay> holidays = tradingDayService.getHolidays(days);
    //Extract the days when the market closes early
    Set<TradingDay> earlyCloseDays = tradingDayService.getEarlyCloseDays(days);
```

Alternatively, you can retrieve holidays for a date range and country

```java
    HolidayService holidayService = new EnricoHolidayService();
    List<HolidaySupport> countries = holidayService.getSupportedCountries();
    List<Holiday> holidays = holidayService.getHolidaysForYear(2015, "usa");
    //Find where a particular date is a holiday
    Map<Country, List<Holiday>> countries =
        holidayService.whereIsPublicHoliday(LocalDate.of(2025, 7, 5));
```