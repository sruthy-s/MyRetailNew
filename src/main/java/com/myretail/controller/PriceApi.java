package com.myretail.controller;

import com.myretail.model.Price;
import com.myretail.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product/{productId}/price")
public class PriceApi {
    @Autowired
    PriceService priceService;

    @GetMapping
    public Price getPriceForProductId(@PathVariable(name = "productId") int productId) {
        return priceService.getPrice(productId);
    }

    @PutMapping
    public void updatePriceForProductId(@PathVariable(name = "productId") int productId, @RequestBody Price price) {
        priceService.savePrice(productId, price);
    }

    @PostMapping
    public void createPriceForProductId(@PathVariable(name = "productId") int productId, @RequestBody Price price) {
        priceService.savePrice(productId, price);
    }
}
