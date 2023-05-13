package com.nishikant.recaptcha.v2.demo.service;

import com.nishikant.recaptcha.v2.demo.dto.GoogleRecaptchaResponse;

@FunctionalInterface
public interface RecaptchaService {
    GoogleRecaptchaResponse verify(String userResponseToken);
}
