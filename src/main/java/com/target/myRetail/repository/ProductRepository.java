package com.target.myRetail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.target.myRetail.model.Product;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface ProductRepository extends MongoRepository<Product, String>{
    public Product findProductById(String id);
}
