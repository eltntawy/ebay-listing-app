package com.app.listing.ebay.services;

import com.mycompany.myapp.ebay.api.ApiClient;
import com.mycompany.myapp.ebay.api.auth.OAuth;
import com.mycompany.myapp.ebay.api.inventory.InventoryItemApi;
import com.mycompany.myapp.ebay.api.inventory.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class InventoryService {

    Logger logger = Logger.getLogger(InventoryService.class.getName());

    @Autowired
    @Qualifier("inventoryApiClient")
    ApiClient apiClient;

    @Autowired
    AuthService authService;

    @PostConstruct
    public void init () {
        apiClient.setAccessToken(authService.getTokenResponseDto().getAccessToken());
    }

    public List<InventoryItemWithSkuLocaleGroupid> getAllItems() {

        // Configure OAuth2 access token for authorization: Authorization Code
        OAuth authorizationCode = (OAuth) apiClient.getAuthentication("Authorization Code");
        authorizationCode.setAccessToken(authService.getTokenResponseDto().getAccessToken());

        InventoryItemApi apiInstance = new InventoryItemApi(apiClient);

        try {
            InventoryItems inventoryItems = apiInstance.getInventoryItems("10", "1");
            return inventoryItems.getInventoryItems();
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Exception when calling InventoryItemApi#bulkCreateOrReplaceInventoryItem",e);
        }

        return null;

    }


    /**
     * item should be look like the below
     * {
     *    "requests":[
     *       {
     *          "sku":"MQCL2LL/A",
     *          "product":{
     *             "title":"Boston Terriers Collector Plate &quot;All Ears� by Dan Hatala - The Danbury Mint",
     *             "aspects":{
     *                "Country/Region of Manufacture":[
     *                   "United States"
     *                ]
     *             },
     *             "description":"All Ears by Dan Hatala. A limited edition from the collection entitled 'Boston Terriers'. Presented by The Danbury Mint.",
     *             "imageUrls":[
     *                "https://i.ebayimg.com/00/s/OTk5WDc0OQ==/z/54IAAOSw3thbW20V/$_12.JPG?set_id=880000500F"
     *             ]
     *          },
     *          "condition":"USED_EXCELLENT",
     *          "conditionDescription":"Mint condition. Kept in styrofoam case. Never displayed.",
     *          "availability":{
     *             "shipToLocationAvailability":{
     *                "quantity":2
     *             }
     *          }
     *       }
     *    ]
     * }
     * @return
     */
    public List<InventoryItemResponse> createItemOrItems() {
        // Configure OAuth2 access token for authorization: Authorization Code
        OAuth authorizationCode = (OAuth) apiClient.getAuthentication("Authorization Code");
        authorizationCode.setAccessToken(authService.getTokenResponseDto().getAccessToken());

        InventoryItemApi apiInstance = new InventoryItemApi(apiClient);

        try {
            BulkInventoryItem bulkInventoryItem = new BulkInventoryItem();

            InventoryItemWithSkuLocale item = new InventoryItemWithSkuLocale();
            item.setLocale("en_US");
            item.setSku("MQCL2LL/A");
            item.setConditionDescription("USED_EXCELLENT");


            Product product = new Product();
            product.setTitle("IPhone X test test test");
            product.setBrand("IPhone");
//            product.setAspects();
            item.setProduct(product);

            Availability availability = new Availability();
            availability.setShipToLocationAvailability(new ShipToLocationAvailability().quantity(2));
            item.setAvailability(availability);

            bulkInventoryItem.addRequestsItem(item);

            BulkInventoryItemResponse bulkInventoryItemResponse = apiInstance.bulkCreateOrReplaceInventoryItem(bulkInventoryItem);
            return bulkInventoryItemResponse.getResponses();
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Exception when calling InventoryItemApi#bulkCreateOrReplaceInventoryItem",e);
        }

        return null;
    }
}
