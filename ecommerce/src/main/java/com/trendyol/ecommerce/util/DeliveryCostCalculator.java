package com.trendyol.ecommerce.util;


import com.trendyol.ecommerce.dao.*;
import com.trendyol.ecommerce.service.ShoppingCartService;

import java.util.HashSet;
import java.util.Set;

public class DeliveryCostCalculator {

    private Double costPerDelivery;
    private Double costPerProduct;
    private Double fixedCost;

    private Integer numberOfProducts;
    private Integer numberOfDelivery;

    private Double totalDeliveryCost;

    public DeliveryCostCalculator(Double costPerDelivery, Double costPerProduct, Double fixedCost){
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    public Double getCostPerDelivery() {
        return costPerDelivery;
    }

    public Double getCostPerProduct() {
        return costPerProduct;
    }

    public Double getFixedCost() {
        return fixedCost;
    }


    public Double calculateFor(ShoppingCartService cart){

        numberOfDelivery = returnDistinctCategories(cart).size();
        numberOfProducts = returnDistinctProducts(cart).size();

        totalDeliveryCost = (getCostPerDelivery() * numberOfDelivery) + (getCostPerProduct() * numberOfProducts) + getFixedCost();

        return totalDeliveryCost;
    }

    public Set<String> returnDistinctCategories(ShoppingCartService cart){
        Set<String> distinctCategories = new HashSet<String>();

        for(CartItem cartItem : cart.getCartItems())
            distinctCategories.add(cartItem.getProduct().getCategory().getTitle());

        return distinctCategories;
    }

    public Set<String> returnDistinctProducts(ShoppingCartService cart){
        Set<String> distinctProducts= new HashSet<String>();

        for(CartItem cartItem : cart.getCartItems())
            distinctProducts.add(cartItem.getProduct().getTitle());

        return distinctProducts;
    }
}
