package com.ganesh.productservice.services;

import com.ganesh.productservice.DTO.Product;
import com.ganesh.productservice.ThirdPartyClients.CategoryService.ThirdPartyClientCategoryServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("thirdPartyApiCategoryService")
public class ThirdPartyApiCategoryService implements CategoryService{
    ThirdPartyClientCategoryServiceAdapter thirdPartyClientCategoryServiceAdapter;
    @Autowired
    ThirdPartyApiCategoryService(@Qualifier("fakeStoreApiCategoryServiceClient") ThirdPartyClientCategoryServiceAdapter thirdPartyClientCategoryServiceAdapter){
        this.thirdPartyClientCategoryServiceAdapter=thirdPartyClientCategoryServiceAdapter;
    }
    @Override
    public ResponseEntity<String[]> getAllCategories() {
        return thirdPartyClientCategoryServiceAdapter.getAllCategories();
    }

    @Override
    public ResponseEntity<Product[]> getProductsByCategory(String category) {
        return thirdPartyClientCategoryServiceAdapter.getProductsByCategory(category);
    }
}
