package com.app.listing.ebay.utils;

import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class RestTemplateUtil {
    private static RestTemplate restTemplate;

    static {
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());

        restTemplate = new RestTemplate(factory);

        List<ClientHttpRequestInterceptor> requestInterceptors = new ArrayList<ClientHttpRequestInterceptor>();
        requestInterceptors.add(new LoggingRequestInterceptor());
        restTemplate.setInterceptors(requestInterceptors);
    }

    public static RestTemplate getRestTemplate() {
        return restTemplate;
    }

}
