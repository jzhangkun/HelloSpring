package com.kun.hello.context;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {

    /*
     * Build http client with
     * Pooling connection manager {@link http://hc.apache.org/httpcomponents-client-ga/tutorial/html/connmgmt.html#d5e393}
     */
    @Bean
    @Qualifier("pooling")
    public CloseableHttpClient poolingHttpClient() {
        CloseableHttpClient httpClient = HttpClientBuilder
                .create()
                .useSystemProperties()
                .build();
        return httpClient;
    }

    /* Or
     * Build http client with
     * Simple connection manager
     */
    @Bean
    @Qualifier("simple")
    public CloseableHttpClient simpleHttpClient () {
        CloseableHttpClient httpClient = HttpClientBuilder
                .create()
                .setConnectionManager(new BasicHttpClientConnectionManager())
                .build();
        return httpClient;
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory(
            @Autowired @Qualifier("pooling") CloseableHttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        return factory;
    }

    /*
     * Build restTemplate from HttpComponentsClientHttpRequestFactory
     * Rather than the default SimpleClientHttpRequestFactory
     */
    @Bean
    public RestTemplate restTemplate(@Autowired ClientHttpRequestFactory factory) {
        RestTemplate template = new RestTemplate(factory);
        return template;
    }

}
