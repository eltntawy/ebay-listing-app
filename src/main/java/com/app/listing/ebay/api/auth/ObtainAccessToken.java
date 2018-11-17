package com.app.listing.ebay.api.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class ObtainAccessToken {

    @GetMapping("obtain_access_token")
    public String getAccessToken () {


        return "";
    }
}
