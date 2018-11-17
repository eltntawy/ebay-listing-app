package com.app.listing.ebay.api.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController("/auth/obtain_access_token")
public class ObtainAccessToken {


    Logger logger = Logger.getLogger(ObtainAccessToken.class.getName());

    @GetMapping
    public String getAccessToken(@RequestHeader HttpHeaders headers,
                                 @RequestBody(required = false) String body,
                                 @RequestParam("state") String status,
                                 @RequestParam("code") String code) {

        logger.info("************************* Request *************************");
        logger.info("-------------------- Headers --------------------");
        logger.info(headers.toString());
        logger.info("-------------------- Headers --------------------");
        logger.info("-------------------- Body --------------------");
        logger.info(body);
        logger.info("-------------------- Body --------------------");
        logger.info("-------------------- status --------------------");
        logger.info(status);
        logger.info("-------------------- status --------------------");
        logger.info("-------------------- code --------------------");
        logger.info(code);
        logger.info("-------------------- code --------------------");

        logger.info("-------------------- Access Token (Authorization token) --------------------");
        try {
            String token = URLDecoder.decode(code, "UTF-8");
            logger.info(token);
        } catch (UnsupportedEncodingException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        logger.info("-------------------- Access Token (Authorization token) --------------------");

        logger.info("************************* Request *************************");

        return "You are in the Obtain Access Token API";

    }
}
