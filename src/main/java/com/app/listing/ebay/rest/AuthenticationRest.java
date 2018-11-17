package com.app.listing.ebay.rest;

import com.app.listing.ebay.model.dto.TokenResponseDto;
import com.app.listing.ebay.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class AuthenticationRest {

    Logger logger = Logger.getLogger(AuthenticationRest.class.getName());

    @Autowired
    AuthService authService;


    @GetMapping("/obtain_access_token")
    public TokenResponseDto getAccessToken(@RequestParam("state") String state, @RequestParam("code") String code) throws UnsupportedEncodingException {

        authService.generateToken(code);

        TokenResponseDto token = authService.getTokenResponseDto();

        logRequest(state, code, authService.getTokenResponseDto().toString());
        String result = "You are now connected to Ebay\n"
                + "your data is\n"
                + "access token: " + token.getAccessToken() + "\n"
                + "token token: " + token.getExpiresIn()+ "\n"
                + "access token: " + token.getRefreshToken()+ "\n"
                + "refresh token expire in: " + token.getRefreshTokenExpiresIn()+ "\n"
                + "type: " + token.getTokenType();

        logger.fine(result);

        return token;

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
