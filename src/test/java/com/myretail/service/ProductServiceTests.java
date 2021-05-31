package com.myretail.service;

import com.myretail.exception.ProductException;
import com.myretail.model.CreateProduct;
import com.myretail.model.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductServiceTests {
    @Autowired
    ProductService productService;

    @Test
    public void validateIfNameIsNull() {
        CreateProduct product = new CreateProduct();

        ProductException exception = Assertions.assertThrows(
                ProductException.class,
                () -> productService.createProduct(product)
        );
        assertThat("name cannot be empty in the request").isEqualTo(exception.getMsg());
    }

    @Test
    public void validateIfNameIsEmpty() {
        CreateProduct product = new CreateProduct();
        product.setName("   ");

        ProductException exception = Assertions.assertThrows(
                ProductException.class,
                () -> productService.createProduct(product)
        );
        assertThat("name cannot be empty in the request").isEqualTo(exception.getMsg());
    }

    @Test
    public void validateIfPriceIsNull() {
        CreateProduct product = new CreateProduct();
        product.setName("Product1");

        ProductException exception = Assertions.assertThrows(
                ProductException.class,
                () -> productService.createProduct(product)
        );
        assertThat("Price cannot be empty in the request").isEqualTo(exception.getMsg());
    }

    @Test
    public void validateIfCurrencyCodeIsNull() {
        Price price = new Price();

        CreateProduct product = new CreateProduct();
        product.setName("Product1");
        product.setCurrentPrice(price);

        ProductException exception = Assertions.assertThrows(
                ProductException.class,
                () -> productService.createProduct(product)
        );
        assertThat("currency_code cannot be empty in the request").isEqualTo(exception.getMsg());
    }

    @Test
    public void validateIfCurrencyCodeIsEmpty() {
        Price price = new Price();
        price.setCurrencyCode("");

        CreateProduct product = new CreateProduct();
        product.setName("Product1");
        product.setCurrentPrice(price);

        ProductException exception = Assertions.assertThrows(
                ProductException.class,
                () -> productService.createProduct(product)
        );
        assertThat("currency_code cannot be empty in the request").isEqualTo(exception.getMsg());
    }

    @Test
    public void validatePriceLessThanZero() {
        Price price = new Price();
        price.setCurrencyCode("USD");
        price.setValue(-1);

        CreateProduct product = new CreateProduct();
        product.setName("Product1");
        product.setCurrentPrice(price);

        ProductException exception = Assertions.assertThrows(
                ProductException.class,
                () -> productService.createProduct(product)
        );
        assertThat("Price value should be greater than 0.").isEqualTo(exception.getMsg());
    }

    @Test
    public void validatePriceEqualsZero() {
        Price price = new Price();
        price.setCurrencyCode("USD");
        price.setValue(0);

        CreateProduct product = new CreateProduct();
        product.setName("Product1");
        product.setCurrentPrice(price);

        ProductException exception = Assertions.assertThrows(
                ProductException.class,
                () -> productService.createProduct(product)
        );
        assertThat("Price value should be greater than 0.").isEqualTo(exception.getMsg());
    }
}
