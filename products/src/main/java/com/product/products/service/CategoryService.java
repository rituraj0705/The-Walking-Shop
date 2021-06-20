package com.product.products.service;

import com.product.products.model.Category;
import java.util.List;

public interface CategoryService {
    public Category addCategory(Category category);
    public void removeCategory();
    public List<Category> getAllCategory();
}
