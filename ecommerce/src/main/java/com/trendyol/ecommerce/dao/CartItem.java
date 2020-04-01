package com.trendyol.ecommerce.dao;

import javax.persistence.*;

public class CartItem {

    Product product;
    private Integer quantity;

    public CartItem(Product product, Integer quantity){
        this.product = product;
        this.quantity = quantity;
    }


    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
