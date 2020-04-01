package com.trendyol.ecommerce.dao;

import javax.persistence.*;
import java.math.BigDecimal;

public class Product {

    private String title;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String title, Double price, Category category){
        this.price = price;
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

}
