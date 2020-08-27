package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private int id;
    private Map<Integer, LineItem> lineItemMap = new HashMap<>();
    private UserDetails userDetails;

    public void addToCart(LineItem lineItem) {
        int id = lineItem.getProduct().getId();
        if (!lineItemMap.containsKey(id)){
            lineItemMap.put(id, lineItem);
        } else {
            lineItemMap.get(id).incrementQuantity();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void removeFromCart(Integer productId) {
        lineItemMap.remove(productId);
    }

    public List<LineItem> getLineItemList() {
        return new ArrayList<LineItem>(lineItemMap.values());
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public LineItem getLineItem(Integer productId) { return lineItemMap.get(productId); }

    public float getTotalOrderPriceInFloat(){
        float totalPrice = 0;
        for (LineItem item: getLineItemList()) {
          totalPrice += item.getTotalProductPriceInFloat();
        }
        return totalPrice;
    }

    public String getTotalOrderPrice() {
        return String.valueOf(getTotalOrderPriceInFloat()) + " USD";
    }

    public int countLineItems() {
        int size = 0;
        for (LineItem lineItem: this.getLineItemList()) {
            size += lineItem.getQuantity();
        }
        return size;
    }
}
