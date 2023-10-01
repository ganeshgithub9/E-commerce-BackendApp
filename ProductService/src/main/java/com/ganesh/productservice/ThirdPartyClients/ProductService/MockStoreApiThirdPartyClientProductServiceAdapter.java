package com.ganesh.productservice.ThirdPartyClients.ProductService;

import com.ganesh.productservice.DTO.PriceDTO;
import com.ganesh.productservice.DTO.Product;
import com.ganesh.productservice.DTO.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component("mockStoreApiThirdPartyClientProductServiceAdapter")
public class MockStoreApiThirdPartyClientProductServiceAdapter implements ThirdPartyClientProductServiceAdapter{
    @Override
    public ResponseEntity<Product> createProduct(ProductDTO productDTO) {
        return null;
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
    public ResponseEntity<Product[]> getProducts() {
        return null;
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) {
        return null;
    }

}
