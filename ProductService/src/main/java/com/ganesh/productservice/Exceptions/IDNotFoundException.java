package com.ganesh.productservice.Exceptions;

import com.ganesh.productservice.DTO.Product;

public class IDNotFoundException extends Exception{
    public IDNotFoundException(String msg){
        super(msg);
    }
}
