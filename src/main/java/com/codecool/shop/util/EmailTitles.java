package com.codecool.shop.util;

public enum EmailTitles {
    ORDER_CONFIRM("Codecool Coffee order confirmation"),
    WELCOME("Welcome to Codecool Coffee shop");

    private final String title;

    EmailTitles(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
