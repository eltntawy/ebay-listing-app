package com.app.listing.ebay.api.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RestController("/auth/obtain_access_token")
public class ObtainAccessToken {


    Logger logger = Logger.getLogger(ObtainAccessToken.class.getName());

    @GetMapping
    public String getAccessToken(@RequestHeader HttpHeaders headers, @RequestBody(required = false) String body) {
        return logRequest(headers, body);
    }

    @PostMapping
    public String postAccessToken(@RequestHeader HttpHeaders headers, @RequestBody(required = false) String body) {
        return logRequest(headers, body);
    }

    private String logRequest(HttpHeaders headers, String body) {

        logger.info("-------------------- Headers --------------------");
        logger.info(headers.toString());
        logger.info("-------------------- Headers --------------------");
        logger.info("-------------------- Body --------------------");
        logger.info(body);
        logger.info("-------------------- Body --------------------");

        return "You are in the Obtain Access Token API";
    }
}
