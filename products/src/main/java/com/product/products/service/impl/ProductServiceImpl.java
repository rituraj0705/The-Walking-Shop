package com.product.products.service.impl;

import com.product.products.model.Product;
import com.product.products.repository.CategoryRepository;
import com.product.products.repository.ProductRepository;
import com.product.products.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Product addProduct(Product product) {

        return productRepository.save(product);

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
    @Override
    public Product getProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return product.get();
        }
        return null;
    }
    @Override
    public List<Product> addProducts (List<Product> products) {
        if (log.isInfoEnabled()) {
            log.info("In add products method of product service, adding a list of products.");
        }
        return productRepository.saveAll(products);
    }
}
