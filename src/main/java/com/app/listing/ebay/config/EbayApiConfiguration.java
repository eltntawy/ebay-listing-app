package com.app.listing.ebay.config;

import com.app.listing.ebay.utils.RestTemplateUtil;
import com.mycompany.myapp.ebay.api.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EbayApiConfiguration {


    @Value("${ebay.config.base.url.inventory}")
    String inventoryBasePath;

    @Value("${ebay.config.base.url.account}")
    String accountBasePath;

    @Value("${app.clientId}")
    String clientId;
    @Value("${app.secret}")
    String secret;

    @Bean("inventoryApiClient")
    public ApiClient getInventoryApiClient() {
        ApiClient apiClient = new ApiClient(RestTemplateUtil.getRestTemplate());
        apiClient.setBasePath(inventoryBasePath);
        return apiClient;
    }


    @Bean("accountApiClient")
    public ApiClient getAccountApiClient() {
        ApiClient apiClient = new ApiClient(RestTemplateUtil.getRestTemplate());
        apiClient.setBasePath(inventoryBasePath);
        return apiClient;
    }
}
