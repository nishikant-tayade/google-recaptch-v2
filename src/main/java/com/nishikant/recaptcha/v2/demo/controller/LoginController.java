package com.nishikant.recaptcha.v2.demo.controller;

import com.nishikant.recaptcha.v2.demo.dto.GoogleRecaptchaResponse;
import com.nishikant.recaptcha.v2.demo.service.RecaptchaService;
import com.nishikant.recaptcha.v2.demo.service.RecaptchaServiceViaRestTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final RecaptchaService recaptchaService;

    public LoginController(@Qualifier("RestTemplate") RecaptchaService recaptchaService,
                           @Value("${recaptcha.site.verify.via}") String recaptchaServiceCaller) {
        this.recaptchaService = recaptchaService;
        System.out.println("recaptchaServiceCaller = " + recaptchaServiceCaller);
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/verify")
    public ResponseEntity<Object> verifyUserResponseToken(@RequestParam("g-recaptcha-response") String userResponseToken) {
        GoogleRecaptchaResponse googleRecaptchaResponse = recaptchaService.verify(userResponseToken);
        return new ResponseEntity<>(googleRecaptchaResponse, HttpStatus.OK);
    }
}
