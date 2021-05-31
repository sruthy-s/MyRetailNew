package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Price {
    private float value;
    @JsonProperty(value = "currency_code")
    private String currencyCode;

    public Price() {
    }

    public Price(float value, String currencyCode) {
        this.value = value;
        this.currencyCode = currencyCode;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
