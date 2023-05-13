package com.nishikant.recaptcha.v2.demo.service;

import com.nishikant.recaptcha.v2.demo.configuration.RecaptchaServiceOpterCondition;
import com.nishikant.recaptcha.v2.demo.dto.GoogleRecaptchaResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;

@Service
@Qualifier("RestTemplate")
@Conditional(RecaptchaServiceOpterCondition.class)
public class RecaptchaServiceViaRestTemplate implements RecaptchaService{

    private final RestOperations restTemplate;

    public RecaptchaServiceViaRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GoogleRecaptchaResponse verify(String userResponseToken) {

        // Define the request URL
        String url = "https://www.google.com/recaptcha/api/siteverify";

        // Define the request headers
        HttpHeaders headers = new HttpHeaders();
        /*
        for this media type, we need a converter which will convert www-form-urlencoded content to the request body,
        if not provide no httpmessageconverter exception will be thrown.
        */
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //Ideally should be obtained from database
        String secret = "";

        /*
        Define the request body
        If you use a HashMap, the key-value pairs will be serialized as a JSON object in the request body,
        which we don't want, which we don't want, When you use a MultiValueMap to represent the request body,
        each key-value pair in the map will be encoded as a separate parameter in the request body.
        */
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("secret", secret.strip());
        requestBody.add("response", userResponseToken.strip());

        // Create the request entity with headers and body
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the request and get the response
        ResponseEntity<GoogleRecaptchaResponse> response = restTemplate.postForEntity(url, requestEntity, GoogleRecaptchaResponse.class);

        return response.getBody();
    }
}
