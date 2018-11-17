package com.app.listing.ebay.services;

import com.app.listing.ebay.model.dto.TokenResponseDto;
import com.app.listing.ebay.utils.RestTemplateUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthService {

    Logger logger = Logger.getLogger(AuthService.class.getName());

    @Value("${ebay.auth.url.authorize}")
    String authorizeUrl;
    @Value("${ebay.auth.url.generateToken}")
    String generateTokenUrl;
    @Value("${app.clientId}")
    String clientId;
    @Value("${app.redirectUri}")
    String redirectUrl;
    @Value("${ebay.config.scope.sell.inventory}")
    String scope;

    @Value("${ebay.config.grantType.authorizationCode}")
    String authorizationGrantType;

    @Value("${app.secret}")
    String secret;


    private TokenResponseDto tokenResponseDto;

    /**
     * generate accessToken base on code returned from obtain user consent service
     *
     * @param code as it's return from the ebay service
     * @return
     */
    public void generateToken(String code) throws UnsupportedEncodingException {

        String decodedCode = URLDecoder.decode(code, "UTF8");

        HttpHeaders headers = createHeaders(clientId, secret);

        RestTemplate restTemplate = RestTemplateUtil.getRestTemplate();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("grant_type", authorizationGrantType);
        map.add("code", decodedCode);
        map.add("redirect_uri", redirectUrl);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        try {
            ResponseEntity<TokenResponseDto> response = restTemplate.postForEntity(generateTokenUrl, request, TokenResponseDto.class);

            if (response != null && response.getStatusCode() == HttpStatus.OK) {
                TokenResponseDto tokenDto = response.getBody();
                if (tokenDto != null) {
                    logger.fine("log token getting successfully");
                    this.tokenResponseDto = tokenDto;
                } else {
                    throw new RuntimeException("Token is null");
                }
            }

        } catch (HttpClientErrorException ex) {
            logger.log(Level.SEVERE, ex.getResponseBodyAsString(), ex);
        }
    }

    /**
     * build the url the used to redirect the user to the login page in ebay
     * <p>
     * example:
     * https://auth.sandbox.ebay.com/oauth2/authorize
     * ?client_id=MohamedH-listinga-SBX-eccf1173b-6aa623cf
     * &redirect_uri=Mohamed_Hassan-MohamedH-listin-mprlppm
     * &response_type=code&state=test_status
     * &scope=https://api.ebay.com/oauth/api_scope/sell.inventory
     * &prompt=login
     *
     * @return the correct url to login to ebay and gain access
     */
    public String buildAuthrizeUrlForSellInventoryUrl() {

        String url = authorizeUrl +
                "?client_id=" + clientId +
                "&redirect_uri=" + redirectUrl +
                "&response_type=code&state=test_status" +
                "&scope=" + scope +
                "&prompt=login";

        return url;
    }

    /**
     * build the url the used to authenticate and generate access accessToken
     * <p>
     * example:
     * https://api.sandbox.ebay.com/identity/v1/oauth2/token
     * body
     * grant_type=authorization_code&
     * code=<code is here>
     * &redirect_uri=Mohamed_Hassan-MohamedH-listin-mprlppm
     *
     * @return the correct url to login to ebay and gain access
     */
    public String buildGenerateAccessTokenUrl() {
        return generateTokenUrl;
    }

    public TokenResponseDto getTokenResponseDto() {
        return tokenResponseDto;
    }

    private HttpHeaders createHeaders(String username, String password) {

        HttpHeaders headers = new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return headers;
    }

}
