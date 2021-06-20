package com.product.products.service.impl;

import com.product.products.model.Category;
import com.product.products.model.Product;
import com.product.products.repository.CategoryRepository;
import com.product.products.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void removeCategory() {

    }

    @Override
    public List<Category> getAllCategory() {
        return null;
    }
}
