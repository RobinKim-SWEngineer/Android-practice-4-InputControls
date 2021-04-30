package com.example.androidcafe;

public enum Dessert {
    donut( "donut", "Donut is ordered"),
    iceCream("iceCream", "IceCream is ordered"),
    froyo("Froyo", "Froyo is ordered");

    private final String orderMessage;
    private final String product;

    Dessert(String product, String orderMessage) {
        this.product = product;
        this.orderMessage = orderMessage;
    }

    public String getOrderMessage() {
        return this.orderMessage;
    }

    public String getOrderProduct() {
        return this.product;
    }
}
