package com.walking.user.userservice.service.product;

import com.walking.user.userservice.vo.Product;
import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getProductById(Long productId);
}
