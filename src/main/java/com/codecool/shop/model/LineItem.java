package com.codecool.shop.model;

public class LineItem {
    private Product product;
    private int quantity;
    private Order order;

    public LineItem(Product product, Order order) {
        this.product = product;
        this.order = order;
        this.quantity = 1;
    }

    public float getTotalProductPriceInFloat(){
        return product.getPriceInFloat()*quantity;
    }

    public String getTotalProductPrice() {
        return String.valueOf(getTotalProductPriceInFloat()) + " " + product.getDefaultCurrency().toString();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
        if (quantity <= 0) order.removeFromCart(this);
    }
}
