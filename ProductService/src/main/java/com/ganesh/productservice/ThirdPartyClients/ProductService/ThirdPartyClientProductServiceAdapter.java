package com.ganesh.productservice.ThirdPartyClients.ProductService;

import com.ganesh.productservice.DTO.PriceDTO;
import com.ganesh.productservice.DTO.Product;
import com.ganesh.productservice.DTO.ProductDTO;
import com.ganesh.productservice.Exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;

public interface ThirdPartyClientProductServiceAdapter {
    public ResponseEntity<Product> createProduct(ProductDTO productDTO);
    public ResponseEntity<Product> updateProduct(ProductDTO productDTO,Long id);
    public ResponseEntity<Product> updatePrice(PriceDTO priceDTO, Long id);
    public ResponseEntity<Product> deleteProductById(Long id);
    public ResponseEntity<Product[]> getProducts();
    ResponseEntity<Product> getProductById(Long id);
}
