package com.codecool.shop.model.json;

import com.codecool.shop.model.LineItem;

public class LineItemUpdatesJson {
    int productId;
    int quantity;
    String totalProductPrice;

    public LineItemUpdatesJson(LineItem lineItem) {
        setProductId(lineItem.getProduct().getId());
        setQuantity(lineItem.getQuantity());
        setTotalProductPrice(lineItem.getTotalProductPrice());
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
}
