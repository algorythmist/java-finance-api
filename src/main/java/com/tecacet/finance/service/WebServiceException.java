package com.tecacet.finance.service;

import javax.ws.rs.core.Response;

public class WebServiceException extends Exception {

    private static final long serialVersionUID = 3435491867108786091L;
    
    private final Response response;

    public WebServiceException(Response response) {
        super(response.getStatusInfo().getReasonPhrase());
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
    
    

}
