package com.trendyol.ecommerce.service;

import com.trendyol.ecommerce.dao.*;
import com.trendyol.ecommerce.util.DeliveryCostCalculator;
import java.text.Collator;
import java.util.*;

public class ShoppingCartService {

    List<CartItem> cartItems;

    private Double totalAmountBeforeDiscounts;

    private Double totalAmountAfterDiscounts;

    private Double totalCampaingDiscount;

    private Double totalCouponDiscount;

    private Double totalDeliveryCost;

    private Collator trCollator = Collator.getInstance(new Locale("tr", "TR"));


    public ShoppingCartService(){

        cartItems = new ArrayList<CartItem>();
        totalAmountBeforeDiscounts=0.0;
        totalAmountAfterDiscounts=0.0;
        totalCampaingDiscount=0.0;
        totalCouponDiscount=0.0;

    }


    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addItem(Product product, Integer quantity){
        CartItem addedCartItem = new CartItem(product, quantity);
        cartItems.add(addedCartItem);

        totalAmountBeforeDiscounts += product.getPrice() * quantity;
    }

    public void applyDiscounts(Campaign campaign1, Campaign campaign2, Campaign campaign3){

        for(CartItem cartItem: cartItems){

            Double maxDiscount = applyDiscountForCartItem(campaign1, campaign2, campaign3, cartItem);
            totalAmountBeforeDiscounts -= maxDiscount ;
            totalCampaingDiscount += maxDiscount;
        }

        totalAmountAfterDiscounts = totalAmountBeforeDiscounts;
    }

    public Double applyDiscountForCartItem(Campaign campaign1, Campaign campaign2, Campaign campaign3, CartItem cartItem){
        Double totalDiscountForCampaing1 = 0.0;
        Double totalDiscountForCampaing2 = 0.0;
        Double totalDiscountForCampaing3 = 0.0;

        if((cartItem.getProduct().getCategory() == campaign1.getCategory()) && (cartItem.getQuantity() > campaign1.getLimitQuantity())){
            totalDiscountForCampaing1 = (cartItem.getProduct().getPrice() * cartItem.getQuantity()) * 0.20;
        }
        if(cartItem.getProduct().getCategory() == campaign2.getCategory()  && (cartItem.getQuantity() > campaign2.getLimitQuantity())){
            totalDiscountForCampaing2 = (cartItem.getProduct().getPrice() * cartItem.getQuantity()) * 0.50;
        }
        if(cartItem.getProduct().getCategory() == campaign3.getCategory()  && (cartItem.getQuantity() > campaign3.getLimitQuantity())){
            totalDiscountForCampaing3 = 5.00;
        }

        return Math.max(totalDiscountForCampaing1,Math.max(totalDiscountForCampaing2,totalDiscountForCampaing3));
    }


    public void applyCoupon(Coupon coupon){

        if(totalAmountAfterDiscounts > coupon.getPurchaseAmount()){
            if(coupon.getDiscountType() == DiscountType.AMOUNT) {
                totalAmountAfterDiscounts -= coupon.getDiscount();
                totalCouponDiscount += coupon.getDiscount();
            } else if(coupon.getDiscountType() == DiscountType.RATE) {
                Double discount = (totalAmountAfterDiscounts * (coupon.getDiscount()/100.0));
                totalAmountAfterDiscounts -= discount;
                totalCouponDiscount += discount;
            }
        }

    }

    public Double getTotalAmountAfterDiscounts(){
        return totalAmountAfterDiscounts;
    }

    public Double getCouponDiscount(){
        return totalCouponDiscount;
    }

    public Double getCampaingDiscount(){
        return totalCampaingDiscount;
    }

    public Double getDeliveryCost(){

        return totalDeliveryCost;
    }

    public void calculateDeliveryCost(DeliveryCostCalculator deliveryCostCalculator){
        totalDeliveryCost = deliveryCostCalculator.calculateFor(this);

    }

    public void print(){
        Collections.sort(cartItems, Comparator.comparing((CartItem cartItem) -> cartItem.getProduct().getCategory().getTitle(), trCollator));

        for(CartItem cartItem: cartItems)
            System.out.println("Category name: "  + cartItem.getProduct().getCategory().getTitle() + ", Product name: "
                    + cartItem.getProduct().getTitle() + ", Quantity: " + cartItem.getQuantity() + ", Unit Price: " + cartItem.getProduct().getPrice() + ", Total Price: "
                    + (cartItem.getProduct().getPrice() * cartItem.getQuantity()));

        System.out.println("Total Campaign discount: " + getCampaingDiscount() + ", Total Coupon discount: " + getCouponDiscount());

        System.out.println("Total Amount: " + getTotalAmountAfterDiscounts() + ", Total Delivery Cost: " + getDeliveryCost());
    }

}
