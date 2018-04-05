package com.target.myRetail.controller;


import com.target.myRetail.Service.ProductService;
import com.target.myRetail.repository.ProductRepository;
import com.target.myRetail.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController

@RequestMapping(value = "/")
public class myRetailController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

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
    public Product getProduct (@PathVariable("id")String id) throws IOException {


        Product productMyRetail = productRepository.findProductById(id);
        Product productRedSky = productService.getProduct(id);

        if (productMyRetail != null && productRedSky != null) {
            // Combine the results from the RedSky query.
            // There should be a freshness check on the cache data
            // I don't there that every call should hit redsky unless it needs to as this defeats the purpose of the cache.
            System.out.println("\n-------myRetail--------");
            productMyRetail.setId(productRedSky.getId());
            productMyRetail.setPrice(productRedSky.getPrice());
            productMyRetail.setName(productRedSky.getName());
            //productRepository.save(productMyRetail2);  // save the product to the cache.
            return productMyRetail;
        }
        else {
            System.out.println("\n-------redSky--------");
            productRepository.save(productRedSky);  // save the product to the cache.
            return productRedSky;
        }
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
