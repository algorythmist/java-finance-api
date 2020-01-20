package com.tecacet.finance.service.yahoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stijn on 23/05/2017.
 */
public class CrumbManager {

    private static final String HISTQUOTES2_CRUMB = System.getProperty("yahoofinance.crumb", "");
    private static final String HISTQUOTES2_COOKIE = System.getProperty("yahoofinance.cookie", "");
    private static final String HISTQUOTES2_SCRAPE_URL = "https://finance.yahoo.com/quote/%5EGSPC/options";
    private static final String HISTQUOTES2_CRUMB_URL = "https://query1.finance.yahoo.com/v1/test/getcrumb";

    private static final int CONNECTION_TIMEOUT = 10000;

    private static final Logger logger = LoggerFactory.getLogger(CrumbManager.class);

    private static String crumb = "";
    private static String cookie = "";

    private static void setCookie() throws IOException {
        if (HISTQUOTES2_COOKIE != null && !HISTQUOTES2_COOKIE.isEmpty()) {
            cookie = HISTQUOTES2_COOKIE;
            logger.debug("Set cookie from system property: {}", cookie);
            return;
        }

        URL request = new URL(HISTQUOTES2_SCRAPE_URL);
        RedirectableRequest redirectableRequest = new RedirectableRequest(request, 5);
        redirectableRequest.setConnectTimeout(CONNECTION_TIMEOUT);
        redirectableRequest.setReadTimeout(CONNECTION_TIMEOUT);
        URLConnection connection = redirectableRequest.openConnection();

        for (String headerKey : connection.getHeaderFields().keySet()) {
            if ("Set-Cookie".equalsIgnoreCase(headerKey)) {
                for (String cookieField : connection.getHeaderFields().get(headerKey)) {
                    for (String cookieValue : cookieField.split(";")) {
                        if (cookieValue.matches("B=.*")) {
                            cookie = cookieValue;
                            logger.debug("Set cookie from http request: {}", cookie);
                            return;
                        }
                    }
                }
            }
        }
        logger.warn("Failed to set cookie from http request. Historical quote requests will most likely fail.");
    }

    private static void setCrumb() throws IOException {
        if (HISTQUOTES2_CRUMB != null && !HISTQUOTES2_CRUMB.isEmpty()) {
            crumb = HISTQUOTES2_CRUMB;
            logger.debug("Set crumb from system property: {}", crumb);
            return;
        }

        URL crumbRequest = new URL(HISTQUOTES2_CRUMB_URL);
        RedirectableRequest redirectableCrumbRequest = new RedirectableRequest(crumbRequest, 5);
        redirectableCrumbRequest.setConnectTimeout(CONNECTION_TIMEOUT);
        redirectableCrumbRequest.setReadTimeout(CONNECTION_TIMEOUT);

        Map<String, String> requestProperties = new HashMap<>();
        requestProperties.put("Cookie", cookie);

        URLConnection crumbConnection = redirectableCrumbRequest.openConnection(requestProperties);

        InputStreamReader is = new InputStreamReader(crumbConnection.getInputStream());
        BufferedReader br = new BufferedReader(is);
        String crumbResult = br.readLine();

        if (crumbResult != null && !crumbResult.isEmpty()) {
            crumb = crumbResult.trim();
        } else {
            logger.warn("Failed to set crumb from http request. Historical quote requests will most likely fail.");
        }
    }

    public static void refresh() throws IOException {
        setCookie();
        setCrumb();
    }

    public static String getCrumb() throws IOException {
        if (crumb == null || crumb.isEmpty()) {
            refresh();
        }
        return crumb;
    }

    public static String getCookie() throws IOException {
        if (cookie == null || cookie.isEmpty()) {
            refresh();
        }
        return cookie;
    }

}
