package com.tecacet.finance.service.yahoo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class RedirectableRequest {

    private final URL request;
    private final int protocolRedirectLimit;

    private int connectTimeout = 10000;
    private int readTimeout = 10000;

    public RedirectableRequest(URL request, int protocolRedirectLimit) {
        this.request = request;
        this.protocolRedirectLimit = protocolRedirectLimit;
    }

    public URLConnection openConnection() throws IOException {
        return openConnection(new HashMap<>());
    }

    public URLConnection openConnection(Map<String, String> requestProperties) throws IOException {
        int redirectCount = 0;
        boolean hasResponse = false;
        HttpURLConnection connection = null;
        URL currentRequest = this.request;
        while (!hasResponse && (redirectCount <= this.protocolRedirectLimit)) {
            connection = (HttpURLConnection) currentRequest.openConnection();
            connection.setConnectTimeout(this.connectTimeout);
            connection.setReadTimeout(this.readTimeout);

            for (String requestProperty : requestProperties.keySet()) {
                connection.addRequestProperty(requestProperty, requestProperties.get(requestProperty));
            }

            // only handle protocol redirects manually...
            connection.setInstanceFollowRedirects(true);

            switch (connection.getResponseCode()) {
                case HttpURLConnection.HTTP_MOVED_PERM:
                case HttpURLConnection.HTTP_MOVED_TEMP:
                    redirectCount++;
                    String location = connection.getHeaderField("Location");
                    currentRequest = new URL(request, location);
                    break;
                default:
                    hasResponse = true;
            }
        }

        if (redirectCount > this.protocolRedirectLimit) {
            throw new IOException("Protocol redirect count exceeded for url: " + this.request.toExternalForm());
        } else {
            return connection;
        }
    }

    public URL getRequest() {
        return request;
    }

    public int getProtocolRedirectLimit() {
        return protocolRedirectLimit;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}
