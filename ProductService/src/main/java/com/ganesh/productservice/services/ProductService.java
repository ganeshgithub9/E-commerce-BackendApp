package com.ganesh.productservice.services;

import com.ganesh.productservice.DTO.PriceDTO;
import com.ganesh.productservice.DTO.Product;
import com.ganesh.productservice.DTO.ProductDTO;
import com.ganesh.productservice.DTO.ProductList;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.Exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<Product> getProductById(Long id) throws ProductNotFoundException;
    ResponseEntity<Product> createProduct(ProductDTO productDTO);
    ResponseEntity<Product> updateProduct(ProductDTO productDTO,Long id);
    ResponseEntity<Product> updatePrice(PriceDTO priceDTO, Long id) throws ProductNotFoundException;

    ResponseEntity<Product> deleteProductById(Long id) throws IDNotFoundException;

    /*ResponseEntity<ProductList>*/ ResponseEntity<Product[]> getProducts();
}
