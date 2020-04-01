package com.trendyol.ecommerce.dao;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

public class Coupon {

    private Double purchaseAmount;
    private Double discount;
    DiscountType discountType;


    public Coupon(Double purchaseAmount, Double discount, DiscountType discountType){
        this.purchaseAmount = purchaseAmount;
        this.discount = discount;
        this.discountType = discountType;
    }

    public Double getPurchaseAmount() {
        return purchaseAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

}
