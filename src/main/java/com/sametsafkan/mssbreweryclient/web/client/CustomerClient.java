package com.sametsafkan.mssbreweryclient.web.client;

import com.sametsafkan.mssbreweryclient.web.model.Customer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "mss.customer", ignoreUnknownFields = false)
public class CustomerClient {

    private String apihost;

    private final String path_v1 = "/api/v1/customer/";

    final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public Customer findById(UUID id){
        return restTemplate.getForObject(createUri() + id.toString(), Customer.class);
    }

    public URI save(Customer customer){
        return restTemplate.postForLocation(createUri(), customer);
    }

    public void update(UUID id, Customer customer){
        restTemplate.put(createUri() + id.toString(), customer);
    }

    public void delete(UUID id){
        restTemplate.delete(createUri() + id.toString());
    }

    private String createUri(){
        return apihost + path_v1;
    }
}
