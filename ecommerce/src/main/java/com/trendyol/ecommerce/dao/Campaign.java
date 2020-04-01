package com.trendyol.ecommerce.dao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

public class Campaign {

    Category category;
    private Double discount;
    private Integer limitQuantity;
    DiscountType discountType;


    public Campaign(Category category, Double discount, Integer limitQuantity, DiscountType discountType){
        this.category = category;
        this.discount = discount;
        this.limitQuantity = limitQuantity;
        this.discountType = discountType;
    }

    public Category getCategory() {
        return category;
    }

    public Double getDiscount() {
        return discount;
    }

    public Integer getLimitQuantity() {
        return limitQuantity;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

}
