package com.tecacet.finance.service;

import com.tecacet.finance.service.WebServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class WebUtil {

    public static String getResponseAsString(String urlString) throws IOException {
        InputStream is = getResponseAsStream(urlString);
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(is))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }

    public static InputStream getResponseAsStream(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            String message = String.format("Connect to %s failed with response code %d and message: %s", urlString, connection.getResponseCode(),
                    connection.getResponseMessage());
            throw new WebServiceException(message);
        }
        return connection.getInputStream();
    }

}
