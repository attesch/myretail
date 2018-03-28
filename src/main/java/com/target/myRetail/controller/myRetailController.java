package com.target.myRetail.controller;


import com.target.myRetail.repository.ProductRepository;
import com.target.myRetail.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController

@RequestMapping(value = "/")
public class myRetailController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    @ResponseBody
    public String helloRoot (){
        return "{\"status\":\"online\"}";
    }

    @GetMapping("/products")
    @ResponseBody
    public List<Product> getAll () {
       return productRepository.findAll();
    }

    @GetMapping(value = "/products/{id}", produces = "application/json")
    @ResponseBody
    public Product getProduct (@PathVariable("id")String id)  {
       return productRepository.findProductById(id);
    }

    @PutMapping("products/{id}")
    @ResponseBody
    public void updateProduct (@Valid @RequestBody Product product) {
        productRepository.save(product);
    }

    @PostMapping(value = "/products", consumes = "application/json")
    @ResponseBody
    public void addProduct (@Valid @RequestBody Product product) {
        productRepository.save(product);
    }

}
