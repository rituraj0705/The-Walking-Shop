package com.product.products.service;

import com.product.products.model.Product;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product product);
    public List<Product> getAllProducts();
    public void deleteProduct(Product product);
    public void deleteProductById(Long id);
    public List<Product> addProducts (List<Product> products);

    public Product getProductById(Long productId);
}
