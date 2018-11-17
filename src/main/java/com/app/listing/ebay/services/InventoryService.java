package com.app.listing.ebay.services;

import com.mycompany.myapp.ebay.api.ApiClient;
import com.mycompany.myapp.ebay.api.auth.OAuth;
import com.mycompany.myapp.ebay.api.inventory.InventoryItemApi;
import com.mycompany.myapp.ebay.api.inventory.model.BulkInventoryItem;
import com.mycompany.myapp.ebay.api.inventory.model.BulkInventoryItemResponse;
import com.mycompany.myapp.ebay.api.inventory.model.InventoryItemWithSkuLocaleGroupid;
import com.mycompany.myapp.ebay.api.inventory.model.InventoryItems;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

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

    public List<InventoryItemWithSkuLocaleGroupid> getAllItems() {
        apiClient.setAccessToken(authService.getTokenResponseDto().getAccessToken());

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
}
