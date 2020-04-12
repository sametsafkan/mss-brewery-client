package com.sametsafkan.mssbreweryclient.web.client;

import com.sametsafkan.mssbreweryclient.web.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    CustomerClient client;

    @Test
    void findById() {
        Customer c = client.findById(UUID.randomUUID());
        assertNotNull(c.getId());
    }

    @Test
    void save() {
        URI uri = client.save(new Customer().builder().build());
        assertNotNull(uri);
    }

    @Test
    void update() {
        client.update(UUID.randomUUID(), new Customer().builder().build());
    }

    @Test
    void delete() {
        client.delete(UUID.randomUUID());
    }
}