package com.codecool.shop.model.json;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;

public class LineItemUpdatesJson {
    int productId;
    int quantity;
    String totalProductPrice;
    String totalOrderPrice;
    int sizeOfCart;

    public LineItemUpdatesJson(LineItem lineItem, Order order) {
        setProductId(lineItem.getProduct().getId());
        setQuantity(lineItem.getQuantity());
        setTotalProductPrice(lineItem.getTotalProductPrice());
        setTotalOrderPrice(order.getTotalOrderPrice());
        setSizeOfCart(order.getLineItemList().size());
    }

    public String getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(String totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(String totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public int getSizeOfCart() {
        return sizeOfCart;
    }

    public void setSizeOfCart(int sizeOfCart) {
        this.sizeOfCart = sizeOfCart;
    }
}
