package com.app.listing.ebay.api.home;

import com.app.listing.ebay.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    AuthService authService;

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        modelAndView.addObject("obtainAccessUrl",authService.buildAuthorizeUrlForSellInventoryUrl());
        return modelAndView;
    }
}
