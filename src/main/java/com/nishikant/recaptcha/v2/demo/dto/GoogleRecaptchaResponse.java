package com.nishikant.recaptcha.v2.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class GoogleRecaptchaResponse {

    private String success;

    @JsonProperty(value = "hostname")
    private String hostName;

    @JsonProperty(value = "error_codes")
    private String[] errorCodes;

    public GoogleRecaptchaResponse() {
    }

    public GoogleRecaptchaResponse(String success, String hostName, String[] errorCodes) {
        this.success = success;
        this.hostName = hostName;
        this.errorCodes = errorCodes;
    }

    public String getSuccess() {
        return success;
    }

    public String getHostName() {
        return hostName;
    }

    public String[] getErrorCodes() {
        return errorCodes;
    }

    @Override
    public String toString() {
        return "GoogleRecaptchaResponse{" +
                "success='" + success + '\'' +
                ", hostName='" + hostName + '\'' +
                ", errorCodes=" + Arrays.toString(errorCodes) +
                '}';
    }
}
