package com.ganesh.productservice.ThirdPartyClients.ProductService;

import com.ganesh.productservice.DTO.*;
import com.ganesh.productservice.Exceptions.ProductNotFoundException;
import com.ganesh.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component("fakeStoreApiProductServiceClient")
public class FakeStoreApiProductServiceClient{
    RestTemplate restTemplate;
    String productUrl="https://fakestoreapi.com/products",productUrlWithId="https://fakestoreapi.com/products/{id}";
    @Autowired
    FakeStoreApiProductServiceClient(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    public ResponseEntity<Product> getProductById(Long id){
        ResponseEntity<Product> product=restTemplate.getForEntity(productUrlWithId,Product.class,id);
        //ResponseEntity<Product> product=restTemplate.getForEntity("https://fakestoreapi.com/products/"+id,Product.class);
        return product;
    }

    public ResponseEntity<Product> createProduct(ProductDTO productDTO){
        ResponseEntity<Product> product=restTemplate.postForEntity(productUrl,productDTO,Product.class);
        return product;
    }

    public ResponseEntity<Product> updateProduct(ProductDTO productDTO,Long id){
        restTemplate.put(productUrlWithId,productDTO,id);
        //return getProductById(id);
        System.out.println("product updated");
        Product p=new Product();
        p.setId(id);
        p.setTitle(productDTO.getTitle());
        p.setDescription(productDTO.getDescription());
        p.setImage(productDTO.getImage());
        p.setCategory(productDTO.getCategory());
        p.setPrice(productDTO.getPrice());
        p.setRating(productDTO.getRating());
        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    public ResponseEntity<Product> updatePrice(PriceDTO priceDTO, Long id) {
        ResponseEntity<Product> productResponseEntity=getProductById(id);
        Product product=productResponseEntity.getBody();
//        if(product==null)
//            throw new ProductNotFoundException("Product with id "+id+" not found");
        //product.setPrice(priceDTO.getPrice());
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    public ResponseEntity<Product> deleteProductById(Long id){
        ResponseEntity<Product> productResponseEntity=getProductById(id);
        restTemplate.delete(productUrlWithId,id);
        return productResponseEntity;
    }


    public /*ResponseEntity<ProductList>*/ ResponseEntity<Product[]> getProducts() {
        System.out.println("Executing service getProducts method");
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity=new HttpEntity<>("parameters",headers);
        //ResponseEntity<ProductList> listResponseEntity=restTemplate.exchange(getProductsUrl, HttpMethod.GET,entity, ProductList.class);
        ResponseEntity<Product[]> listResponseEntity=restTemplate.exchange(productUrl, HttpMethod.GET,entity, Product[].class);
        System.out.println("Ending service getProducts method");
        return listResponseEntity;
    }

    public ResponseEntity<Product[]> getProductsByCategory(String category){
        ResponseEntity<Product[]> responseEntity=restTemplate.getForEntity(productUrl+"/category/"+category,Product[].class);
        return responseEntity;
    }
}
