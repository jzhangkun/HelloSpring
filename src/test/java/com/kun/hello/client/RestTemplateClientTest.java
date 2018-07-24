package com.kun.hello.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RestTemplateClientTest {
    private RestTemplate restTemplate = mock(RestTemplate.class);
    private String url = "test_url";

    private RestTemplateClient restTemplateClient;

    @Before
    public void setup() {
        restTemplateClient = new RestTemplateClient(restTemplate, url);
    }

    @Test
    public void testPost() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok().body("test response message"));
        restTemplateClient.post("test request message");
        verify(restTemplate).exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class));
    }


}
