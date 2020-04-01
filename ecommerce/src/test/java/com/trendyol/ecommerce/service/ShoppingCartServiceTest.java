package com.trendyol.ecommerce.service;


import com.trendyol.ecommerce.dao.*;
import com.trendyol.ecommerce.util.DeliveryCostCalculator;
import org.junit.Test;

public class ShoppingCartServiceTest {

    @Test
    public void testModel() {

        Category category = new Category("food");

        Product apple = new Product("Apple", 100.0, category);
        Product almond = new Product("Almonds", 150.0, category);
        Product banana = new Product("Banana", 40.0, category);

        ShoppingCartService cart = new ShoppingCartService();
        cart.addItem(apple, 4);
        cart.addItem(almond, 6);
        cart.addItem(banana, 1);

        Campaign campaign1 = new Campaign(category, 20.0, 3, DiscountType.RATE);
        Campaign campaign2 = new Campaign(category, 50.0, 5, DiscountType.RATE);
        Campaign campaign3 = new Campaign(category, 5.0, 5, DiscountType.AMOUNT);

        cart.applyDiscounts(campaign1, campaign2, campaign3);

        Coupon coupon = new Coupon(100.0, 10.0, DiscountType.RATE);

        cart.applyCoupon(coupon);

        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(1.0,1.0,2.5);

        cart.calculateDeliveryCost(deliveryCostCalculator);

        cart.print();

    }


}
