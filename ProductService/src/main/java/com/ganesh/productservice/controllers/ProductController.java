package com.ganesh.productservice.controllers;

import com.ganesh.productservice.DTO.PriceDTO;
import com.ganesh.productservice.DTO.Product;
import com.ganesh.productservice.DTO.ProductDTO;
import com.ganesh.productservice.DTO.ProductList;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.Exceptions.ProductNotFoundException;
import com.ganesh.productservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
public abstract class ProductController {
    ProductService productService;
    abstract ResponseEntity<Product> getProductById(Long id) throws ProductNotFoundException;

    abstract ResponseEntity<Product> createProduct(ProductDTO productDTO);


    abstract ResponseEntity<Product> updateProduct(ProductDTO productDTO,Long id);

    abstract ResponseEntity<Product> updatePrice(PriceDTO priceDTO, Long id) throws ProductNotFoundException;

    abstract ResponseEntity<Product> deleteProductById(Long id) throws IDNotFoundException;

    abstract /*ResponseEntity<List<Product>>*/ ResponseEntity<Product[]>  getProducts();
}
