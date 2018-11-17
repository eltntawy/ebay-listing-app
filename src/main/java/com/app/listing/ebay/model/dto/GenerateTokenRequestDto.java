package com.app.listing.ebay.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenerateTokenRequestDto {

    @JsonProperty("code")
    String grantType;

    @JsonProperty("code")
    String code;

    @JsonProperty("redirect_uri")
    String redirect_uri;


    public GenerateTokenRequestDto(String grantType, String code, String redirect_uri) {
        this.grantType = grantType;
        this.code = code;
        this.redirect_uri = redirect_uri;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }
}
