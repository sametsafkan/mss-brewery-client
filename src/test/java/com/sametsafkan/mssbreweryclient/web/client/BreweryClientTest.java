package com.sametsafkan.mssbreweryclient.web.client;

import com.sametsafkan.mssbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void findById() {
        BeerDto beer = client.findById(UUID.randomUUID());
        assertNotNull(beer.getId());
    }

    @Test
    void save(){
        BeerDto beerDto = new BeerDto().builder().build();
        URI uri = client.save(beerDto);
        assertNotNull(uri);
    }

    @Test
    void update(){
        BeerDto beerDto = new BeerDto().builder().build();
        client.update(UUID.randomUUID(), beerDto);
    }

    @Test
    void delete(){
        client.delete(UUID.randomUUID());
    }
}