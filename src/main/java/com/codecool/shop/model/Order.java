package com.codecool.shop.model;

import java.util.List;

public class Order {
    private List<LineItem> lineItemList;
    private UserDetails userDetails;

    public void addToCart(LineItem lineItem) {
        lineItemList.add(lineItem);
    }

    public void removeFromCart(LineItem lineItem) {
        lineItemList.remove(lineItem);
    }

    public List<LineItem> getLineItemList() {
        return lineItemList;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }
}
