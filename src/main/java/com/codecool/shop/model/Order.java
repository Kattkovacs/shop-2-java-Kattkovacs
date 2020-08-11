package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private Map<Integer, LineItem> lineItemList = new HashMap<>();
    private UserDetails userDetails;

    public void addToCart(LineItem lineItem) {
        int id = lineItem.getProduct().getId();
        if (!lineItemList.containsKey(id)){
            lineItemList.put(id, lineItem);
        } else {
            lineItemList.get(id).incrementQuantity();
        }
    }

    public void removeFromCart(LineItem lineItem) {
        lineItemList.remove(lineItem);
    }

    public List<LineItem> getLineItemList() {
        return new ArrayList<LineItem>(lineItemList.values());
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }
}
