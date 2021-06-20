package com.walking.user.userservice.service.impl;

import com.walking.user.userservice.service.product.ProductService;
import com.walking.user.userservice.vo.Product;
import com.walking.user.userservice.vo.ProductList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<Product> getAllProducts() {
        String url = "http://PRODUCT-SERVICE/products/all";//"http://localhost:9091/products/all
        if (log.isInfoEnabled()) {
            log.info("URL is {}", url);
        }
        Product[] products = restTemplate.getForObject(url, Product[].class);
        return Arrays.asList(products);
    }

    @Override
    public Product getProductById(Long productId) {
        String url = "http://PRODUCT-SERVICE/products/" + productId;//"http://localhost:9091/products/" + productId;PRODUCT-SERVICE
        if (log.isInfoEnabled()) {
            log.info("URL is {}", url);
        }
        Product product =
                restTemplate.getForObject(url, Product.class);
        return product;
    }
}
