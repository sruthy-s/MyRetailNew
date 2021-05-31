package com.myretail.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Price")
public class PriceEntity {
    @Id
    @Column(name = "product_id")
    private int productId;
    private String currency;
    private float value;

    public PriceEntity() {
    }

    public PriceEntity(int productId, String currency, float value) {
        this.productId = productId;
        this.currency = currency;
        this.value = value;
    }

    public int getProductId() {
        return productId;
    }

    public String getCurrency() {
        return currency;
    }

    public float getValue() {
        return value;
    }
}
