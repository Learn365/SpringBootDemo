package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.exception.ProductNotFoundException;

@RestController
public class ProductServiceController {

    @Autowired
    ProductService productService;

    @RequestMapping(value="/products")
    public ResponseEntity<Object> getProducts(){
        return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);
    }

    @RequestMapping(value="/products",method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        productService.createProduct(product);
        return new ResponseEntity<>("Product is created successfully",HttpStatus.CREATED);
    }

    @RequestMapping(value="/products/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        productService.updateProduct(id,product);
        return new ResponseEntity<>("Product is updated successfully",HttpStatus.OK);
    }

    @RequestMapping(value="/products/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product is deleted successfully",HttpStatus.OK);
    }

}
