package com.app.listing.ebay.api.auth;

import com.app.listing.ebay.model.dto.TokenResponseDto;
import com.app.listing.ebay.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

@RestController("/auth/obtain_access_token")
public class ObtainAccessTokenRest {

    Logger logger = Logger.getLogger(ObtainAccessTokenRest.class.getName());

    @Autowired
    AuthService authService;


    @GetMapping
    public String getAccessToken(@RequestParam("state") String state, @RequestParam("code") String code) throws UnsupportedEncodingException {

        authService.generateToken(code);

        TokenResponseDto token = authService.getTokenResponseDto();

        logRequest(state, code, authService.getTokenResponseDto().toString());
        String result = "You are now connected to Ebay\n"
                + "your data is\n"
                + "access token: \n" + token.getAccessToken()
                + "token token: \n" + token.getExpiresIn()
                + "access token: \n" + token.getRefreshToken()
                + "refresh token expire in: \n" + token.getRefreshTokenExpiresIn()
                + "type: \n" + token.getTokenType();

        return result;

    }


    private void logRequest(String state, String code, String accessToken) {
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
