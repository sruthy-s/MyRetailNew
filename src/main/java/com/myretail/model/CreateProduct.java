package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateProduct {
    private String name;
    @JsonProperty(value = "current_price")
    private Price currentPrice;

    public CreateProduct() {
    }

    public CreateProduct(String name, Price currentPrice) {
        this.name = name;
        this.currentPrice = currentPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Price getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Price currentPrice) {
        this.currentPrice = currentPrice;
    }
}
