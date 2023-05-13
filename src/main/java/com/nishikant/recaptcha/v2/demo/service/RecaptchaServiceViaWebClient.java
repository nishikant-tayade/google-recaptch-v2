package com.nishikant.recaptcha.v2.demo.service;

import com.nishikant.recaptcha.v2.demo.configuration.RecaptchaServiceOpterCondition;
import com.nishikant.recaptcha.v2.demo.dto.GoogleRecaptchaResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Qualifier("WebClient")
@Conditional(RecaptchaServiceOpterCondition.class)
public class RecaptchaServiceViaWebClient implements RecaptchaService{

    private final WebClient webClient;

    public RecaptchaServiceViaWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public GoogleRecaptchaResponse verify(String userResponseToken) {

        System.out.println("Verifying using Web Client");

        String url = "https://www.google.com/recaptcha/api/siteverify";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String secret = "";

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("secret", secret.strip());
        requestBody.add("response", userResponseToken.strip());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        Mono<GoogleRecaptchaResponse> response = webClient.post()
                .uri(url)
                .body(BodyInserters.fromFormData(requestBody))
                .retrieve()
                .bodyToMono(GoogleRecaptchaResponse.class);

        return response.block();
    }

}
