package com.ganesh.productservice.services;

import com.ganesh.productservice.DTO.PriceDTO;
import com.ganesh.productservice.DTO.Product;
import com.ganesh.productservice.DTO.ProductDTO;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.Exceptions.ProductNotFoundException;
import com.ganesh.productservice.ThirdPartyClients.ProductService.ThirdPartyClientProductServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("thirdPartyApiProductService")
public class ThirdPartyApiProductService implements ProductService{
    ThirdPartyClientProductServiceAdapter thirdPartyClientProductServiceAdapter;

    @Autowired
    ThirdPartyApiProductService(@Qualifier("fakeStoreApiThirdPartyClientProductServiceAdapter") ThirdPartyClientProductServiceAdapter thirdPartyClientProductServiceAdapter){
        this.thirdPartyClientProductServiceAdapter=thirdPartyClientProductServiceAdapter;
    }
    @Override
    public ResponseEntity<Product> getProductById(Long id) throws ProductNotFoundException {
        //return thirdPartyClientProductServiceAdapter.getProductById(id);
        ResponseEntity<Product> productResponseEntity=thirdPartyClientProductServiceAdapter.getProductById(id);
//        try {
//            if (productResponseEntity.getBody() == null)
//                throw new ProductNotFoundException("Product with id " + id + "not found");
//        }
//        catch (ProductNotFoundException exception){
//            System.out.println(exception.getMessage());
//        }
        if (productResponseEntity.getBody() == null)
                throw new ProductNotFoundException("Product with id " + id + "not found");
        return productResponseEntity;
    }

    @Override
    public ResponseEntity<Product> createProduct(ProductDTO productDTO) {
        return thirdPartyClientProductServiceAdapter.createProduct(productDTO);
    }

    @Override
    public ResponseEntity<Product> updateProduct(ProductDTO productDTO, Long id) {
        return thirdPartyClientProductServiceAdapter.updateProduct(productDTO,id);
    }

    @Override
    public ResponseEntity<Product> updatePrice(PriceDTO priceDTO, Long id) throws ProductNotFoundException{
        ResponseEntity<Product> responseEntity= thirdPartyClientProductServiceAdapter.updatePrice(priceDTO,id);
        if(responseEntity.getBody()==null)
            throw new ProductNotFoundException("Product with id "+id+" not found");
        Product product=responseEntity.getBody();product.setPrice(priceDTO.getPrice());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> deleteProductById(Long id) throws IDNotFoundException {
        ResponseEntity<Product> responseEntity= thirdPartyClientProductServiceAdapter.deleteProductById(id);
        if(responseEntity.getBody()==null)
            throw new IDNotFoundException("Product with id "+id+" not found");
        return responseEntity;
    }

    @Override
    public ResponseEntity<Product[]> getProducts() {
        return thirdPartyClientProductServiceAdapter.getProducts();
    }
}
