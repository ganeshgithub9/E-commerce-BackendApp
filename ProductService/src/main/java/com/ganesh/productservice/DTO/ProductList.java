package com.ganesh.productservice.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ProductList{
    private List<Product> list;
    public ProductList(){
        list=new ArrayList<>();
    }
    public List<Product> getProducts(){
        return list;
    }
//    public void setList(List<Product> products){
//        list=products;
//    }
//    public List<Product> getList(){
//        return list;
//    }
}
