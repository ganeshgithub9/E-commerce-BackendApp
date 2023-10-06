package com.ganesh.productservice.services;

import com.ganesh.productservice.DTO.Product;
import org.springframework.http.ResponseEntity;

public interface CategoryService{
    ResponseEntity<String[]> getAllCategories();
    ResponseEntity<Product[]> getProductsByCategory(String category);
}
