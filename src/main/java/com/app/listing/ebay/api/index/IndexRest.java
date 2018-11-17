package com.app.listing.ebay.api.index;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class IndexRest {


    @GetMapping
    public String welcome() {
        return "Welcome To Ebay Listing App!!";
    }
}
