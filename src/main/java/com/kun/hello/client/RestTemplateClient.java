package com.kun.hello.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateClient {
    Logger logger = LoggerFactory.getLogger(RestTemplateClient.class);
    private RestTemplate restTemplate;
    private String url;

    public RestTemplateClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public void post(String message) {
        HttpHeaders header = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<String>(message, header);
        logger.info("Request: " + request.toString());
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        logger.info("Response: " + response.toString());
    }
}
