package com.trendyol.ecommerce.dao;

import javax.persistence.*;
import java.util.Set;

public class Category {

    private String title;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="category")
    private Set<Product> products;

    public Category(String title){

        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
