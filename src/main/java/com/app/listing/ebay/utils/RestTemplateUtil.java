package com.app.listing.ebay.utils;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class RestTemplateUtil {
    private static RestTemplate rt = new RestTemplate();

    static {
        List<ClientHttpRequestInterceptor> ris = new ArrayList<ClientHttpRequestInterceptor>();
        ris.add(new LoggingRequestInterceptor());
        rt.setInterceptors(ris);
    }

    public static RestTemplate getRestTemplate() {
        return rt;
    }

}
