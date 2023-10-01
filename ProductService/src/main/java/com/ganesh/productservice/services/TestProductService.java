package com.ganesh.productservice.services;

import com.ganesh.productservice.DTO.PriceDTO;
import com.ganesh.productservice.DTO.Product;
import com.ganesh.productservice.DTO.ProductDTO;
import com.ganesh.productservice.DTO.ProductList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testProductService")
public class TestProductService implements ProductService{
    @Override
    public ResponseEntity<Product> getProductById(Long id) {
        Product product=new Product();
        product.setTitle("returning the id");
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<Product> createProduct(ProductDTO productDTO){
        Product product1=new Product();
        product1.setTitle("product created");
        return new ResponseEntity<>(product1,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Product> updateProduct(ProductDTO productDTO, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Product> updatePrice(PriceDTO priceDTO, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Product> deleteProductById(Long id) {
        return null;
    }

    @Override
    public /*ResponseEntity<ProductList>*/ ResponseEntity<Product[]> getProducts() {
        return null;
    }
}
