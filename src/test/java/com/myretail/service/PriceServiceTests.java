package com.myretail.service;

import com.myretail.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PriceServiceTests {
    @Autowired
    PriceService priceService;

    @Test
    void createAndGetPrice() {
        priceService.savePrice(1, new Price(12, "INR"));
        Price price = priceService.getPrice(1);
        assertThat(12f).isEqualTo(price.getValue());
        assertThat("INR").isEqualTo(price.getCurrencyCode());
    }

    @Test
    void getPriceInvalidId() {
        Price price = priceService.getPrice(2);
        assertThat(price).isEqualTo(null);
    }
}
