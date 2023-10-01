package com.ganesh.productservice.ThirdPartyClients.ProductService;

import com.ganesh.productservice.DTO.PriceDTO;
import com.ganesh.productservice.DTO.Product;
import com.ganesh.productservice.DTO.ProductDTO;
import com.ganesh.productservice.Exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component("fakeStoreApiThirdPartyClientProductServiceAdapter")
public class FakeStoreApiThirdPartyClientProductServiceAdapter implements ThirdPartyClientProductServiceAdapter{

    FakeStoreApiProductServiceClient fakeStoreApiProductServiceClient;

    @Autowired
    FakeStoreApiThirdPartyClientProductServiceAdapter(FakeStoreApiProductServiceClient fakeStoreApiProductServiceClient){
        this.fakeStoreApiProductServiceClient=fakeStoreApiProductServiceClient;
    }
    @Override
    public ResponseEntity<Product> createProduct(ProductDTO productDTO) {
        return fakeStoreApiProductServiceClient.createProduct(productDTO);
    }

    @Override
    public ResponseEntity<Product> updateProduct(ProductDTO productDTO, Long id) {
        return fakeStoreApiProductServiceClient.updateProduct(productDTO,id);
    }

    @Override
    public ResponseEntity<Product> updatePrice(PriceDTO priceDTO, Long id){
        return fakeStoreApiProductServiceClient.updatePrice(priceDTO,id);
    }

    @Override
    public ResponseEntity<Product> deleteProductById(Long id) {
        return fakeStoreApiProductServiceClient.deleteProductById(id);
    }

    @Override
    public ResponseEntity<Product[]> getProducts() {
        return fakeStoreApiProductServiceClient.getProducts();
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) {
        return fakeStoreApiProductServiceClient.getProductById(id);
    }
}
