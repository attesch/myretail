package com.target.myRetail.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Product {

    @Id
    @NotNull
    // Needs a validation to ensure this string matches product id rules.
    public String id;

    @NotNull
    private String name;

    @NotNull
    @Positive
    public Double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
