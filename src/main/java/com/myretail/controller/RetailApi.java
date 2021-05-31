package com.myretail.controller;

import com.myretail.model.CreateProduct;
import com.myretail.model.Product;
import com.myretail.model.UpdateProduct;
import com.myretail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/product")
public class RetailApi {
    @Autowired
    private ProductService productService;
    @Value("${api.base.url}")
    private String apiBaseUrl;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody CreateProduct p) {
        int id = productService.createProduct(p);
        URI uri = URI.create(apiBaseUrl + id);
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id, @RequestBody UpdateProduct updateProduct) {
        Product product = productService.updateProduct(id, updateProduct);
        return ResponseEntity.ok(product);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProducts(@PathVariable("id") int id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
}
