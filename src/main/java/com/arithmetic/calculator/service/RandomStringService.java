package com.arithmetic.calculator.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RandomStringService {
    private static final String RANDOM_URL = "https://www.random.org/strings/?num=1&len=10&digits=on&upper=on&lower=on&unique=on&format=plain&rnd=new";

    public String getRandomString() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(RANDOM_URL, String.class);
    }
}