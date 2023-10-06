package com.ganesh.productservice.services;

import com.ganesh.productservice.DTO.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("selfCategoryService")
public class SelfCategoryService implements CategoryService{
    @Override
    public ResponseEntity<String[]> getAllCategories() {
        return null;
    }

    @Override
    public ResponseEntity<Product[]> getProductsByCategory(String category) {
        return null;
    }
}
