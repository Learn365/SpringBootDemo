package com.example.demo.controller;

import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class ConsumeWebService {

    @Value("${server.port:8080}")
    private String port;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value="/template/products")
    public String getProductList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate
                .exchange("http://localhost:" + port + "/products", HttpMethod.GET, entity, String.class)
                .getBody();
    }

    @RequestMapping(value="/template/products",method = RequestMethod.POST)
    public String createProducts(@RequestBody Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);

        return restTemplate.exchange(
                String.format("http://localhost:%s/products", port), HttpMethod.POST, entity, String.class)
                .getBody();
    }

    @RequestMapping(value="/template/products/{id}",method = RequestMethod.PUT)
    public String updateProduct(@PathVariable("id") String id,@RequestBody Product product){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);

        return restTemplate.exchange(
                String.format("http://localhost:%s/products/%s", port,id), HttpMethod.PUT, entity, String.class)
                .getBody();
    }

    @RequestMapping(value="/template/products/{id}",method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable("id") String id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(headers);

        return restTemplate.exchange(
                String.format("http://localhost:%s/products/%s", port,id), HttpMethod.DELETE, entity, String.class)
                .getBody();
    }
}
