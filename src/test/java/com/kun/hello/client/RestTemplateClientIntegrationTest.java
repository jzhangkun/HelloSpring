package com.kun.hello.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.kun.hello.context.HttpClientConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {HttpClientConfig.class})
@TestPropertySource(properties = {"environment=local"})
public class RestTemplateClientIntegrationTest {
    @Autowired
    private RestTemplate restTemplate;
    private String url = "http://localhost:8091/hello/session-test";

    private RestTemplateClient restTemplateClient;

    ObjectMapper jsonMapper = new ObjectMapper();

    // expect to get same session id
    @Test
    public void testSessionWithCustomizeRestTemplate() {
        restTemplateClient = new RestTemplateClient(restTemplate, url);
        System.out.println(restTemplateClient);
        List<ResponseEntity<String>> result = Lists.newArrayList();

        for (int i = 0; i < 5; i++) {
            result.add(restTemplateClient.get());
        }
        result.forEach(System.out::println);

        String lastSessionId;
        ResponseEntity<String> r = result.get(0);
        String jsessionId = getJSessionId(r.getHeaders().getFirst(HttpHeaders.SET_COOKIE));
        String currSessionId = getSessionId(r.getBody());

        System.out.println(jsessionId + " " + currSessionId);
    }

    // expect to get the different session id
    @Test
    public void testSessionWithDefaultRestTemplate() {
        restTemplateClient = new RestTemplateClient(new RestTemplate(), url);
        System.out.println(restTemplateClient);
        List<ResponseEntity<String>> result = Lists.newArrayList();

        for (int i = 0; i < 5; i++) {
            result.add(restTemplateClient.get());
        }
        result.forEach(System.out::println);
    }

    private String getJSessionId(String cookie) {
        Objects.requireNonNull(cookie);
        if (cookie.indexOf("JSESSIONID=") != -1) {
            return cookie.split(";")[0].split("=")[1];
        }
        return null;
    }
    private String getSessionId(String session) {
        Objects.requireNonNull(session);
        if (session.indexOf("SessionID=") != -1) {
            return session.split("=")[1];
        }
        return null;
    }

}
