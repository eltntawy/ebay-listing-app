package com.app.listing.ebay.api.auth;

import com.app.listing.ebay.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController("/auth/obtain_access_token")
public class ObtainAccessTokenRest {

    Logger logger = Logger.getLogger(ObtainAccessTokenRest.class.getName());

    @Autowired
    AuthService authService;


    @GetMapping
    public String getAccessToken(@RequestParam("state") String state, @RequestParam("code") String code) throws UnsupportedEncodingException {

        String accessToken = authService.generateToken(code);

        logRequest(state,code,accessToken);
        return "Good work";

    }


    private void logRequest( String state, String code,String accessToken) {
        logger.info("************************* Request *************************");
        logger.info("-------------------- state --------------------");
        logger.info(state);
        logger.info("-------------------- state --------------------");
        logger.info("-------------------- code --------------------");
        logger.info(code);
        logger.info("-------------------- code --------------------");
        logger.info("-------------------- Access Code --------------------");
        logger.info(code);
        logger.info("-------------------- Access code --------------------");
        logger.info("-------------------- Access Token (Authorization token) --------------------");
        logger.info(accessToken);
        logger.info("-------------------- Access Token (Authorization token) --------------------");


        logger.info("************************* Request *************************");

    }
}
