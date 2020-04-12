package com.sametsafkan.mssbreweryclient.web.client;

import com.sametsafkan.mssbreweryclient.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@ConfigurationProperties(value = "mss.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private String apihost;

    private final RestTemplate restTemplate;
    private final String path_v1 = "/api/v1/beer/";

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto findById(UUID id){
        return restTemplate.getForObject(apihost + path_v1 + id, BeerDto.class);
    }
}
