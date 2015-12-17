package com.tecacet.finance.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class WebUtil {

    private static final int OK = 200;

    public static String getResponseAsString(String url) throws WebServiceException {
        Response response = getURLResponse(url);
        return response.readEntity(String.class);
    }
    
    public static Response getURLResponse(String url) throws WebServiceException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(url);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN_TYPE);
        Response response = invocationBuilder.get();
        int status = response.getStatus();
        if (status != OK) {
            throw new WebServiceException(response);
        }
        return response;
    }

  

}
