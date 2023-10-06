package com.ganesh.productservice.ThirdPartyClients.ProductService;

import com.ganesh.productservice.DTO.Product;
import org.springframework.http.ResponseEntity;

public interface ThirdPartyClientCategoryServiceAdapter {
    public ResponseEntity<String[]> getAllCategories();
    public ResponseEntity<Product[]> getProductsByCategory(String category);
}
