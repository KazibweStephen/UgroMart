package com.ugromart.platform.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class ProductsRestConnector {

    @Value("${service.products.url}")
    private String url;
    private RestTemplate restTemplate;


    public ProductsRestConnector(){
        this.restTemplate=new RestTemplate();
    }

    public <T> T get(Class<T> responseType, String endPoint){

        return restTemplate.getForObject(url+endPoint,responseType);
    }

}
