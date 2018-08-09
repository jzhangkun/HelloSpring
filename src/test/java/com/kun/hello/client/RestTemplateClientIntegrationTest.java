package com.kun.hello.client;

import com.google.common.collect.Lists;
import com.kun.hello.context.HttpClientConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {HttpClientConfig.class})
@TestPropertySource(properties = {"environment=local"})
public class RestTemplateClientIntegrationTest {
    @Autowired
    private RestTemplate restTemplate;
    private String url = "http://localhost:8091/hello/session-test";

    private RestTemplateClient restTemplateClient;

    // expect to get same session id
    @Test
    public void testSessionWithCustomizeRestTemplate() {
        restTemplateClient = new RestTemplateClient(restTemplate, url);
        System.out.println(restTemplateClient);
        List<String> result = Lists.newArrayList();

        for (int i = 0; i < 5; i++) {
            result.add(restTemplateClient.get());
        }
        result.forEach(System.out::println);
    }

    // expect to get the different session id
    @Test
    public void testSessionWithDefaultRestTemplate() {
        restTemplateClient = new RestTemplateClient(new RestTemplate(), url);
        System.out.println(restTemplateClient);
        List<String> result = Lists.newArrayList();

        for (int i = 0; i < 5; i++) {
            result.add(restTemplateClient.get());
        }
        result.forEach(System.out::println);
    }
}
