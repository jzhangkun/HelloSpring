package com.kun.hello.client;

import com.google.common.collect.Maps;
import org.neo4j.ogm.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestTemplateClient {
    Logger logger = LoggerFactory.getLogger(RestTemplateClient.class);
    private RestTemplate restTemplate;
    private String url;

    public RestTemplateClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public ResponseEntity<String> get() {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        logger.debug("Response: " + response.toString());
        return response;
    }

    public void post(String message) {
        HttpHeaders header = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<String>(message, header);
        logger.debug("Request: " + request.toString());
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        logger.debug("Response: " + response.toString());
    }

    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append("RestTemplate Client ")
                .append(restTemplate.toString())
                .append(" route to URL ")
                .append(url);
        return builder.toString();
    }
}
